package com.example.sushant.udacityproject8_newsapp;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 14/9/16.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsInfo>> {
    public String NEWS_REQUEST_URL = "http://content.guardianapis.com/search?q=football&api-key=test&show-fields=thumbnail&section=football&order-by=newest&show-tags=contributor";

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<NewsInfo> loadInBackground() {
        ArrayList<NewsInfo> newsInfoList = new ArrayList<>();
        URL url = createURLObject(NEWS_REQUEST_URL);
        try {
            String jsonResponse = makeHTTPRequest(url);

            newsInfoList = extractNews(jsonResponse);
        } catch (Exception e) {
            Log.e("", "" + e);
        }

        return newsInfoList;
    }

    private URL createURLObject(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException exception) {
            Log.e("Error with URL:", "" + exception);
        }
        Log.e("", "URL OBJECT CREATED");

        return url;
    }

    private String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = null;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            Log.e("URL Connection Failed:", "" + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        Log.e("", "HTTP REQUEST MADE");

        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder streamOutput = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                streamOutput.append(line);
                line = bufferedReader.readLine();
            }
        }
        return streamOutput.toString();
    }

    private ArrayList<NewsInfo> extractNews(String jsonResponse) {
        ArrayList<NewsInfo> newsInfoArrayList = new ArrayList<NewsInfo>();

        try {
            JSONObject rootObject = new JSONObject(jsonResponse);
            JSONObject responseObject = rootObject.getJSONObject("response");
            JSONArray resultsArray = responseObject.getJSONArray("results");

            if (resultsArray.length() > 0) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject articleObject = resultsArray.getJSONObject(i);
                    String newsTitle = articleObject.optString("webTitle");
                    String newsURL = articleObject.optString("webUrl");

                    JSONObject fieldsObject = articleObject.getJSONObject("fields");
                    String newsThumbnail = fieldsObject.optString("thumbnail");
                    JSONArray tagsArray = articleObject.getJSONArray("tags");
                    String newsAuthor = null;
                    if (tagsArray.length() > 0) {
                        JSONObject authorObject = tagsArray.getJSONObject(0);
                        newsAuthor = authorObject.optString("webTitle");
                    }
                    NewsInfo newsInfo = new NewsInfo(newsTitle, newsThumbnail, newsURL, newsAuthor);
                    newsInfoArrayList.add(newsInfo);
                }
            }
        } catch (Exception e) {
            Log.e("JSON ERROR:", "" + e);
        }
        return newsInfoArrayList;
    }


}