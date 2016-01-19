package com.pandamnapp.ifound;

import android.app.ProgressDialog;
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

    ArrayList<ITuneSearchObject> searchObjectsArrayList = new ArrayList<>();
    private HomeScreen homeScreen;
    private String RESULTS = "results";
    private String TRACK_NAME = "trackName";
    private String ART_WORK30 = "artworkUrl30";
    private String ART_WORK100 = "artworkUrl100";
    private String SHORT_DESC = "shortDescription";
    private String LONG_DESC = "longDescription";
    private String KIND = "kind";
    private String ARTIST = "artistName";
    private String Genre = "primaryGenreName";
    private String TRACK_PRICE = "trackPrice";
    private MainActivity mainActivity;
    private ProgressDialog pDialog;


    public SearchDownloader(HomeScreen homeScreen) {
        this.homeScreen = homeScreen;
    }

    public SearchDownloader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(homeScreen);
        pDialog.setMessage("Loading");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        pDialog.dismiss();
        SearchAdapter adapter = new SearchAdapter(homeScreen, searchObjectsArrayList);
        homeScreen.mListView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL apiUrl;
        HttpURLConnection mainconn;
        InputStream maininputStream;
        String trackName = null;
        String artist = null;
        String artworkUrl30 = null;
        String artworkUrl100 = null;
        String shortDes = null;
        String longDes = null;
        String genre = null;
        String kind = null;
        String trackPrice = null;

        try {
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
            JSONArray dataArray = jsonObject.getJSONArray(RESULTS);

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject singleObject = dataArray.getJSONObject(i);
                if (singleObject.has(KIND)) {
                    kind = singleObject.getString(KIND);
                }
                if (singleObject.has(ARTIST)){
                    artist = singleObject.getString(ARTIST);
                }
                if (singleObject.has(TRACK_NAME)) {
                    trackName = singleObject.getString(TRACK_NAME);
                }
                if (singleObject.has(ART_WORK30)) {
                    artworkUrl30 = singleObject.getString(ART_WORK30);
                }
                if (singleObject.has(ART_WORK100)){
                    artworkUrl100 = singleObject.getString(ART_WORK100);
                }
                if (singleObject.has(SHORT_DESC)) {
                    shortDes = singleObject.getString(SHORT_DESC);
                }
                if (singleObject.has(LONG_DESC)) {
                    longDes = singleObject.getString(LONG_DESC);
                }
                if (singleObject.has(TRACK_PRICE)) {
                    trackPrice = singleObject.getString(TRACK_PRICE);
                }
                if (singleObject.has(Genre)){
                    genre = singleObject.getString(Genre);
                }
                ITuneSearchObject iTuneSearchObject = new ITuneSearchObject(trackName, artworkUrl30, artworkUrl100, shortDes, longDes,kind, trackPrice,artist,genre);
                searchObjectsArrayList.add(iTuneSearchObject);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
