package com.example.marck.whatsfordinner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.marck.whatsfordinner.dataaccess.BlackListRepository;
import com.example.marck.whatsfordinner.dataaccess.FavouritelistRepository;


public class DetailsActivity extends ActionBarActivity {

    private final int MENU_FAVOURITES = 0;
    private final int MENU_BLACKLIST = 1;
    private final int MENU_SETTINGS = 2;
    private final int MENU_QUIT = 3;

    private String title;
    private String imageSrc;
    private String link;

    public static final String DOMAIN = "http://mobile.chefkoch.de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent myIntent = getIntent();
        title = myIntent.getStringExtra("title");
        imageSrc = myIntent.getStringExtra("imageSrc");
        link = myIntent.getStringExtra("link");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(DOMAIN + link);

        Button hateItButton = (Button) findViewById(R.id.hateIt);
        View.OnClickListener hateItListener = new ClickListener();
        hateItButton.setOnClickListener(hateItListener);

        Button loveItButton = (Button) findViewById(R.id.loveIt);
        View.OnClickListener loveItListener = new ClickListener();
        loveItButton.setOnClickListener(loveItListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favourites) {
            Intent favouritesIntent = new Intent(DetailsActivity.this, FavouritesActivity.class);
            startActivity(favouritesIntent);
            return true;
        } else if (id == R.id.action_blacklist) {
            Intent blackListIntent = new Intent(DetailsActivity.this, BlackListActivity.class);
            startActivity(blackListIntent);
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(DetailsActivity.this, "Les settings", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_quit) {
            quit();
        }

        return super.onOptionsItemSelected(item);

    }

    public void quit(){
        this.finish();
    }

    private class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            BlackListRepository blackListRep = new BlackListRepository();
            FavouritelistRepository favouritesRep = new FavouritelistRepository();

            if(v.getId() == R.id.hateIt) {

                if (blackListRep.isBlackListItemInDb(getBaseContext(), link)){

                    Toast.makeText(DetailsActivity.this, "Rezept ist bereits in der Blacklist", Toast.LENGTH_LONG).show();

                } else if(favouritesRep.isFavouriteListItemInDb(getBaseContext(), link)) {

                    blackListRep.insertIntoBlackListFromFavouriteList(getBaseContext(), title, imageSrc, link);
                    Toast.makeText(DetailsActivity.this, "Rezept von Favourites in Blacklist verschoben", Toast.LENGTH_LONG).show();

                } else {

                    blackListRep.insertIntoBlacklist(getBaseContext(), title, imageSrc, link);
                    Toast.makeText(DetailsActivity.this, "Rezept in Blacklist verschoben", Toast.LENGTH_LONG).show();

                }

            }else if(v.getId() == R.id.loveIt) {

                if (favouritesRep.isFavouriteListItemInDb(getBaseContext(), link)){

                    Toast.makeText(DetailsActivity.this, "Rezept ist bereits in den Favourites", Toast.LENGTH_LONG).show();

                } else if(blackListRep.isBlackListItemInDb(getBaseContext(), link)) {

                    favouritesRep.insertIntoFavouriteListFromBlackList(getBaseContext(), title, imageSrc, link);
                    Toast.makeText(DetailsActivity.this, "Rezept von Blacklist in Favourites verschoben", Toast.LENGTH_LONG).show();

                } else {

                    favouritesRep.insertIntoFavouriteList(getBaseContext(), title, imageSrc, link);
                    Toast.makeText(DetailsActivity.this, "Rezept in die Favourites verschoben", Toast.LENGTH_LONG).show();

                }

            }

        }

    }
}
