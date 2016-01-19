package com.pandamnapp.ifound;

import android.content.Context;
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

public class HomeScreen extends AppCompatActivity {

    //Initializing layout views
    ListView mListView;
    String URL_STRING;
    String entity;
    String term = null;
    private Spinner mSpinner;
    private EditText mSearchParam;
    private Button mSearchButton;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mListView = (ListView) findViewById(R.id.listView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSearchParam = (EditText) findViewById(R.id.search);
        mSearchButton = (Button) findViewById(R.id.searchBtn);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                term = mSearchParam.getText().toString();

                if(term.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter a search term",
                            Toast.LENGTH_LONG).show();
                }
                if (!entity.isEmpty() && !term.isEmpty()){
                    URL_STRING = String.format(getResources().getString(R.string.url1),term);
                    SearchDownloader searchDownloader = new SearchDownloader(HomeScreen.this);
                    searchDownloader.execute();
                    mListView.setVisibility(View.VISIBLE);
                }
                else if (entity.isEmpty() && term.isEmpty()){
                    URL_STRING = String.format(getResources().getString(R.string.url2), term, entity);
                    SearchDownloader searchDownloader = new SearchDownloader(HomeScreen.this);
                    searchDownloader.execute();
                    mListView.setVisibility(View.VISIBLE);
                }
            }
        });

        ArrayAdapter<CharSequence> entityAdapter = ArrayAdapter
                .createFromResource(this, R.array.Entities,
                        R.layout.spinner_item);
        mSpinner.setAdapter(entityAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entity = String.valueOf(mSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                entity = null;
            }
        });
    }
}

