package com.example.marck.whatsfordinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marck.whatsfordinner.model.FavouritelistItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class FavouritesAdapter extends ArrayAdapter<FavouritelistItem> {
    private ArrayList<Boolean> status = new ArrayList<>();
    private final Context context;
    private final FavouritelistItem[] rowData;

    /*
     * Adapter to fetch the strings and images for each row Make sure you store
     * any per-item state in this adapter, not in the Views which may be
     * recycled upon scrolling
     */
    public FavouritesAdapter(Context context, FavouritelistItem[] rowData) {
        super(context, R.layout.listitem, rowData);
        this.context = context;
        this.rowData = rowData;
        for (int i = 0; i < rowData.length; i++) {
            status.add(false);
        }
    }

    /*
     * Get a View that displays the data at the specified position in the data
     * set.
     *
     * @param position -- The position of the item within the adapter's data set
     * of the item whose view we want.
     *
     * @param convertView -- The old view to reuse, if possible. Note: You
     * should check that this view is non-null and of an appropriate type before
     * using. If it is not possible to convert this view to display the correct
     * data, this method can create a new view.
     *
     * @param parent -- The parent that this view will eventually be attached
     * to.
     *
     * @return -- A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (convertView == null) { // there is no convertView available from
            // scrolling that can be reused
            // obtain a rowView by instantiating the row layout XML file
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(R.layout.listitem, parent, false);
        }

        // fill the rowView with data
        if (rowData[position] != null) {
            // obtain references to the views inside the rowView that we'd like
            // to fill with data
            ImageView imageView = (ImageView) rowView.findViewById(R.id.recipeImage);

            if (imageView != null) {
                ImageLoader.getInstance().displayImage(rowData[position].getImageSrc(), imageView, DisplayImageOptions.createSimple());
            }

            TextView textView = (TextView) rowView.findViewById(R.id.title);

            if (textView != null) {
                textView.setText(rowData[position].getTitle());
            }

            TextView shorttextView = (TextView) rowView.findViewById(R.id.shorttext);

            if (shorttextView != null) {
                shorttextView.setText(rowData[position].getLink());
            }

        }
        return rowView;

    }

    public ArrayList<Boolean> getStatus(){
        return status;
    }
}
