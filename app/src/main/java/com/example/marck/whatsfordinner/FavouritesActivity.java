package com.example.marck.whatsfordinner;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class FavouritesActivity extends ListActivity {

    FavouritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int numRows = 64;
        super.onCreate(savedInstanceState);
        Recipe[] rowData = new Recipe[numRows];

        // initialize the array with some data (for demo and debugging purposes
        // only)
        for (int i = 0; i < numRows; i++) {
            rowData[i] = new Recipe("Item " + String.valueOf(i + 1), "lel", "lel");
        }

        // construct and register the adapter
        adapter = new FavouritesAdapter(this, rowData);
        setListAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_black_list, menu);
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
