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

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    String URL_STRING;
    // tracks, albums, videos
    String entity;
    //term: artist name, song name, book name
    String term = null;
    private Spinner mSpinner;
    private EditText mSearchParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSearchParam = (EditText) findViewById(R.id.search);
        Button mSearchButton = (Button) findViewById(R.id.searchBtn);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                term = mSearchParam.getText().toString();

                if (term.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter a search term",
                            Toast.LENGTH_LONG).show();
                }
                if (entity.equals("Select") && term != null) {
                    URL_STRING = String.format(getResources().getString(R.string.url1), term);
                    SearchDownloader searchDownloader = new SearchDownloader(MainActivity.this);
                    searchDownloader.execute();
                    mListView.setVisibility(View.VISIBLE);
                } else if (!entity.equals("Select") && term != null) {
                    URL_STRING = String.format(getResources().getString(R.string.url2), term, entity);
                    SearchDownloader searchDownloader = new SearchDownloader(MainActivity.this);
                    searchDownloader.execute();
                    mListView.setVisibility(View.VISIBLE);
                }
            }
        });

        ArrayAdapter<CharSequence> entityAdapter = ArrayAdapter
                .createFromResource(this, R.array.Entities,
                        android.R.layout.simple_spinner_item);
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
