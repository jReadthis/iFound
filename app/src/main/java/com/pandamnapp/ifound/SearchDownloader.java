package com.pandamnapp.ifound;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Kristhyana on 1/16/2016.
 */
public class SearchDownloader extends AsyncTask<Void, Void, Void> {

    ArrayList<ITuneSearchObject> searchObjectsArrayList =  new ArrayList<>();
    private HomeScreen homeScreen;
    private String RESULTS = "results";
    private String TRACK_NAME = "trackName";
    private String ART_WORK = "artworkUrl30";
    private String SHORT_DESC = "shortDescription";
    private String LONG_DESC = "longDescription";
    private String KIND = "kind";
    private String TRACK_PRICE = "trackPrice";


    public SearchDownloader(HomeScreen homeScreen){
        this.homeScreen = homeScreen;
    }

    public SearchDownloader(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPostExecute(Void avoid){
        super.onPostExecute(avoid);
        SearchAdapter adapter = new SearchAdapter(homeScreen, searchObjectsArrayList);
        homeScreen.mListView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL apiUrl;
        HttpURLConnection mainconn;
        InputStream maininputStream;
        String wrapperType = null;
        String trackName = null;
        String artworkUrl = null;
        String shortDes = null;
        String longDes = null;
        String kind = null;
        String trackPrice = null;
        Bitmap bmp = null;

        try{
            apiUrl = new URL(homeScreen.URL_STRING);

            mainconn = (HttpURLConnection) apiUrl.openConnection();
            maininputStream = mainconn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(maininputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            String json = sb.toString();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray dataArray = jsonObject.getJSONArray("results");

            for(int i = 0; i < dataArray.length(); i++){
                JSONObject singleObject = dataArray.getJSONObject(i);

                try {
                    wrapperType = singleObject.getString("wrapperType");
                } catch (JSONException e) {
                    Log.v("Wrapper", "Something went wrong.");
                }

                try {
                    kind = singleObject.getString("kind");
                } catch (JSONException e) {
                    kind = "No value for kind";
                }

                if (wrapperType != null) {
                    try {
                        switch (wrapperType) {
                            case "artist":
                                trackName = singleObject.getString("artistName");
                                break;
                            case "track":
                                trackName = singleObject.getString("trackName");
                                break;
                            case "collection":
                                trackName = singleObject.getString("collectionName");
                                break;
                        }

                    } catch (JSONException e) {
                        trackName = "No value for track-name";
                    }
                }
                else if(kind != null){
                    try {
                        switch (kind) {
                            case "ebook":
                                trackName = singleObject.getString("artistName");
                                break;
                            case "tv-episode":
                                trackName = singleObject.getString("trackName") +
                                " - " + singleObject.getString("collectionName");
                                break;
                            case "collection":
                                trackName = singleObject.getString("collectionName");
                                break;
                        }

                    } catch (JSONException e) {
                        trackName = "No value for track-name";
                    }
                }

                try {
                    artworkUrl = singleObject.getString("artworkUrl30");
                } catch (JSONException e) {
                    artworkUrl = null;
                    Log.v("Call from background", "No artwork");
                }
                if (artworkUrl != null) {
                    URL downloadURL = new URL(artworkUrl);
                    HttpURLConnection conn = (HttpURLConnection) downloadURL.openConnection();
                    InputStream inputStream = conn.getInputStream();
                    bmp = BitmapFactory.decodeStream(inputStream);
                }

                if (singleObject.has(SHORT_DESC)) {
                    shortDes = singleObject.getString(SHORT_DESC);
                }
                if (singleObject.has(LONG_DESC)) {
                    longDes = singleObject.getString(LONG_DESC);
                }
                try {
                    trackPrice = singleObject.getString("trackPrice");
                } catch (JSONException e) {
                    trackPrice = "0.0";
                }
                
                ITuneSearchObject iTuneSearchObject;
                if(wrapperType != null && kind == null) {
                    iTuneSearchObject = new ITuneSearchObject(trackName, bmp, wrapperType, trackPrice);
                }
                else{
                    iTuneSearchObject = new ITuneSearchObject(trackName, bmp, kind, trackPrice);
                }
                searchObjectsArrayList.add(iTuneSearchObject);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
