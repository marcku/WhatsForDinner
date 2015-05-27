package com.example.marck.whatsfordinner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends ActionBarActivity {

    private final int MENU_FAVOURITES = 0;
    private final int MENU_BLACKLIST = 1;
    private final int MENU_SETTINGS = 2;
    private final int MENU_QUIT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        new RecipeHandler(this).loadRecipes();
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
            Intent favouritesIntent = new Intent(MainActivity.this, FavouritesActivity.class);
            startActivity(favouritesIntent);
            return true;
        } else if (id == R.id.action_blacklist) {
            Intent blackListIntent = new Intent(MainActivity.this, BlackListActivity.class);
            startActivity(blackListIntent);
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Les settings", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_quit) {
            quit();
        }

        return super.onOptionsItemSelected(item);

    }

    public void quit(){
        this.finish();
    }
}
