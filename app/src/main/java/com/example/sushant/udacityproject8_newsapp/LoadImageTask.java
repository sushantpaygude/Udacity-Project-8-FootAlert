package com.example.sushant.udacityproject8_newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sushant on 21/9/16.
 */
public class LoadImageTask extends AsyncTask<String,Void,Bitmap> {
    ImageView newsThumbnailview;

    public LoadImageTask(ImageView newsThumbnailview) {
        this.newsThumbnailview = newsThumbnailview;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urlThumbnail=params[0];
        Bitmap newsThumbnail=null;
        InputStream in=null;
        try {
             in = new java.net.URL(urlThumbnail).openStream();
            newsThumbnail = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return newsThumbnail;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
           newsThumbnailview.setImageBitmap(bitmap);

    }
}
