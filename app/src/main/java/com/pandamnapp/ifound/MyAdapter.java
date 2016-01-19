package com.pandamnapp.ifound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kristhyana on 1/16/2016.
 */
public class MyAdapter extends ArrayAdapter<ITuneSearchObject> {

            Context context;
            ArrayList<ITuneSearchObject> obj;

    public MyAdapter(Context c, ArrayList<ITuneSearchObject> objects) {
            super(c, R.layout.single_row, objects);
            this.context = c;
            this.obj = objects;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.single_row, parent, false);

                TextView trackName = (TextView) row.findViewById(R.id.trackName);
                ImageView artworkUrl30 = (ImageView) row.findViewById(R.id.artWork);
                TextView kind = (TextView) row.findViewById(R.id.kind);
                TextView shortDesc = (TextView) row.findViewById(R.id.shortDes);
                TextView trackPrice = (TextView) row.findViewById(R.id.trackPrice);



                ITuneSearchObject object = obj.get(position);
                trackName.setText(object.trackName);
                kind.setText(object.kind);
                shortDesc.setText(object.shortDescription);
                trackPrice.setText(object.trackPrice);
                return row;
    }

}
