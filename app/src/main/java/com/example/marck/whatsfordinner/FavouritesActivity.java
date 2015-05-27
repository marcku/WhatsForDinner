package com.example.marck.whatsfordinner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marck.whatsfordinner.dataaccess.FavouritelistRepository;
import com.example.marck.whatsfordinner.model.FavouritelistItem;

public class FavouritesActivity extends ListActivity {

    FavouritesAdapter adapter;
    FavouritelistRepository repository = new FavouritelistRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FavouritelistItem[] rowData = repository.getFavouriteListEntries(getBaseContext());

        // construct and register the adapter
        adapter = new FavouritesAdapter(this, rowData);
        setListAdapter(adapter);

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
            Intent favouritesIntent = new Intent(FavouritesActivity.this, FavouritesActivity.class);
            startActivity(favouritesIntent);
            return true;
        } else if (id == R.id.action_blacklist) {
            Intent blackListIntent = new Intent(FavouritesActivity.this, BlackListActivity.class);
            startActivity(blackListIntent);
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(FavouritesActivity.this, "Les settings", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_quit) {
            quit();
        }

        return super.onOptionsItemSelected(item);

    }

    public void quit(){
        this.finish();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);

        Intent detailsIntent = new Intent(FavouritesActivity.this, DetailsActivity.class);

        FavouritelistItem favItem = (FavouritelistItem)this.getListAdapter().getItem(position);
        detailsIntent.putExtra("title", favItem.getTitle());
        detailsIntent.putExtra("link", favItem.getLink());
        detailsIntent.putExtra("imageSrc", favItem.getImageSrc());

        startActivity(detailsIntent);

    }

}
