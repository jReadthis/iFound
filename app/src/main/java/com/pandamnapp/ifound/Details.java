package com.pandamnapp.ifound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Details extends AppCompatActivity {

    TextView artist;
    TextView genre;
    TextView title;
    TextView kind;
    TextView price;
    TextView longDesc;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        artist = (TextView) findViewById(R.id.txtArtist);
        genre = (TextView) findViewById(R.id.txtGenre);
        title = (TextView) findViewById(R.id.txtTitle);
        kind = (TextView) findViewById(R.id.txtKind);
        price = (TextView) findViewById(R.id.txtPrice);
        longDesc = (TextView) findViewById(R.id.txtLongDesc);
        imageView = (ImageView) findViewById(R.id.imageView);


    }


}
