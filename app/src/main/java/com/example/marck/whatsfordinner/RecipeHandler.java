package com.example.marck.whatsfordinner;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.marck.whatsfordinner.model.Recipe;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by marck on 17.03.15.
 */
public class RecipeHandler {
    private Activity mHostActivity;
    private DisplayImageOptions options;
    private ArrayList<Tag> taglist = new ArrayList<>();
    private ArrayList<Recipe> recipeList = new ArrayList<>();

    private static final String LOGTAG = "RecipeActivity";
    private static final int NUM_RECIEPS = 30;
    private int completed = 0;

    private ProgressBar progressBar;

    public RecipeHandler(Activity mHostActivity) {
        this.mHostActivity = mHostActivity;

        progressBar = (ProgressBar) mHostActivity.findViewById(R.id.progressBar);

        this.options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();

    }

    public void loadRecipes() {
        new GetTags().execute();
    }

    private void loadRecipeItems() {
        for (int i = 0; i < NUM_RECIEPS; i++) {
            new DownloadRecipe().execute();
        }
    }

    private void completedLoading() {
        completed++;

        if (completed == NUM_RECIEPS) {
            renderListView();
        }
    }

    private void renderListView() {

        progressBar.setVisibility(View.INVISIBLE);

        ListView listView = (ListView) mHostActivity
                .findViewById(R.id.listRecipeViewId);
        listView.setAdapter(new RecipeItemAdapter(mHostActivity,
                R.layout.listitem,
                recipeList,
                options
        ));
    }

    private class GetTags extends AsyncTask<String, Void, String> {
        String TAG = "GetTags";

        @Override
        protected String doInBackground(String... params) {
            String getURL = "http://mobile.chefkoch.de/mobile/mobile-topsearches.php";

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getURL);
            String content = null;

            try {
                HttpResponse response = client.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    InputStream resp = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(resp));
                    StringBuilder out = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                    content = out.toString();   //Prints the string content read from input stream
                    reader.close();

                    extractTags(content);
                } else {
                    Log.d(TAG, "HTTP Status Code was not 200 / OK");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error loading " + getURL, e);
            }

            return content;
        }

        protected void onPostExecute(String result) {
            loadRecipeItems();
        }


        private void extractTags(String html) {
            Document doc = Jsoup.parse(html);
            Elements ulList = doc.select("ul.linklist.arrowlist li");

            Iterator<Element> elementIterator = ulList.iterator();
            Element currentElement;
            Tag tag;

            while (elementIterator.hasNext()) {
                currentElement = elementIterator.next();
                String text = currentElement.text();
                String link = currentElement.getElementsByTag("a").attr("href");
                link = link.replace("s0", "s0i1");
                Log.d(TAG, text);
                Log.d(TAG, link);
                tag = new Tag(text, link);
                taglist.add(tag);
            }
        }
    }

    private class DownloadRecipe extends AsyncTask<String, Void, String> {
        String TAG = "RecipeHandler";
        boolean querySuccessful = false;

        @Override
        protected String doInBackground(String... params) {

            String content = null;

            int randomNumber = randInt(0, taglist.size()-1);
            int randompPage = randInt(0, 30);
            String getURL = "http://mobile.chefkoch.de" + taglist.get(randomNumber).getLink();
            getURL = getURL.replace("s0i1", "s"+randompPage+"i1");

            Log.d(TAG, "Tag Name: " + taglist.get(randomNumber).getName());
            Log.d(TAG, "GetURL: " + getURL);

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getURL);

            try {
                HttpResponse response = client.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    InputStream resp = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(resp));
                    StringBuilder out = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                    content = out.toString();   //Prints the string content read from input stream
                    reader.close();

                    extractRecipes(content);
                    querySuccessful = true;

                } else {
                    Log.d(TAG, "HTTP Status Code was not 200 / OK");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error loading " + getURL, e);
            }

            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            if (querySuccessful) {
                completedLoading();
            } else {
                // optionally handle the unsuccessful query
            }
        }

        private void extractRecipes(String html) {
            Document doc = Jsoup.parse(html);
            Elements ulList = doc.select("li.recipelist-item");

            Iterator<Element> elementIterator = ulList.iterator();
            Element currentElement;
            Recipe recipe;

            currentElement = elementIterator.next();
            String text = currentElement.text();
            String title = currentElement.getElementsByTag("h2").text();
            String imgSrc = currentElement.getElementsByTag("img").attr("src");
            imgSrc = imgSrc.replace("tiniefix", "bigfix");
            String recipeLink = currentElement.getElementsByTag("a").attr("href");
            recipe = new Recipe(title, imgSrc, recipeLink);
            recipeList.add(recipe);
            Log.d(TAG, "title: " + title);
            Log.d(TAG, "image: " + imgSrc);
            Log.d(TAG, "Link: " + recipeLink);
            Log.d(TAG, "Element content: " + text);
        }
    }

    public int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}