package com.pandamnapp.ifound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView listView;
    Spinner spinner;
    EditText search;
    Button bt;

    String URL_STRING;
    String entity;
    String term = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);
        search = (EditText) findViewById(R.id.search);
        bt = (Button) findViewById(R.id.searchBtn);



        ArrayAdapter<CharSequence> entityAdapter = ArrayAdapter
                .createFromResource(this, R.array.Entities,
                        android.R.layout.simple_spinner_item);
        spinner.setAdapter(entityAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entity = String.valueOf(spinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addURL(View view) {
        term = search.getText().toString();

        if(term == "") {
            Toast.makeText(getBaseContext(), "Please enter a search term",
                    Toast.LENGTH_LONG).show();
        }
        if (entity == "Select" && term != null){
            URL_STRING = String.format(getResources().getString(R.string.url1),term);
            SearchDownloader searchDownloader = new SearchDownloader(this);
            searchDownloader.execute();
            listView.setVisibility(View.VISIBLE);
        }
        else if (entity != "Select" && term !=null){
            URL_STRING = String.format(getResources().getString(R.string.url2), term, entity);
            SearchDownloader searchDownloader = new SearchDownloader(this);
            searchDownloader.execute();
            listView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        entity = String.valueOf(spinner.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        entity = null;
    }
}

