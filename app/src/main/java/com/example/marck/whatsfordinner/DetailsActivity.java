package com.example.marck.whatsfordinner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class DetailsActivity extends ActionBarActivity {

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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
