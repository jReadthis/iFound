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
    private MainActivity mainActivity;


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
        URL apiUrl = null;
        HttpURLConnection mainconn = null;
        InputStream maininputStream = null;
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

                //JSONObject trackObject = singleObject.getJSONObject("trackName");
                //if (singleObject.has("trackName")) {
                    trackName = singleObject.getString("trackName");
                //}
                //if(singleObject.has("artworkUrl")) {
                    artworkUrl = singleObject.getString("artworkUrl30");
                    URL downloadURL = new URL(artworkUrl);
                    HttpURLConnection conn = (HttpURLConnection) downloadURL.openConnection();
                    InputStream inputStream = conn.getInputStream();
                    bmp = BitmapFactory.decodeStream(inputStream);
                //}

                //shortDes = singleObject.getString("shortDescription");
                //if (singleObject.has("kind")) {
                    kind = singleObject.getString("kind");
                //}
                //longDes = singleObject.getString("longDescription");
                //if (singleObject.has("trackPrice")) {
                    trackPrice = singleObject.getString("trackPrice");
                //}
                //ITuneSearchObject iTuneSearchObject = new ITuneSearchObject(trackName,bmp,
                        //shortDes, longDes, kind, trackPrice);
                ITuneSearchObject iTuneSearchObject = new ITuneSearchObject(trackName,bmp, kind, trackPrice);
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
