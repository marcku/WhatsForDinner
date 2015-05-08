package com.example.marck.whatsfordinner;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.marck.whatsfordinner.dataaccess.BlackListRepository;
import com.example.marck.whatsfordinner.model.BlacklistItem;

public class BlackListActivity extends ListActivity {

    BlackListAdapter adapter;
    BlackListRepository repository = new BlackListRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/*        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacklistitem);*/

        BlacklistItem item = new BlacklistItem("lel", 12345L);
        repository.saveBlackListEntry(item);

        super.onCreate(savedInstanceState);
        BlacklistItem[] rowData = repository.getBlacklistEntries();

        // construct and register the adapter
        adapter = new BlackListAdapter(this, rowData);
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
