package com.example.marck.whatsfordinner;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marck.whatsfordinner.dataaccess.BlackListRepository;
import com.example.marck.whatsfordinner.model.BlacklistItem;

public class BlackListActivity extends ListActivity {

    BlackListAdapter adapter;
    BlackListRepository repository = new BlackListRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        BlacklistItem[] rowData = repository.getBlacklistEntries(getBaseContext());

        // construct and register the adapter
        adapter = new BlackListAdapter(this, rowData);
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
            Intent favouritesIntent = new Intent(BlackListActivity.this, FavouritesActivity.class);
            startActivity(favouritesIntent);
            return true;
        } else if (id == R.id.action_blacklist) {
            Intent blackListIntent = new Intent(BlackListActivity.this, BlackListActivity.class);
            startActivity(blackListIntent);
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(BlackListActivity.this, "Les settings", Toast.LENGTH_LONG).show();
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

        Intent detailsIntent = new Intent(BlackListActivity.this, DetailsActivity.class);

        BlacklistItem blItem = (BlacklistItem)this.getListAdapter().getItem(position);
        detailsIntent.putExtra("title", blItem.getTitle());
        detailsIntent.putExtra("link", blItem.getLink());
        detailsIntent.putExtra("imageSrc", blItem.getImageSrc());

        startActivity(detailsIntent);

    }

}
