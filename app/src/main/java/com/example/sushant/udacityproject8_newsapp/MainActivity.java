package com.example.sushant.udacityproject8_newsapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsInfo>>,AdapterView.OnItemClickListener{
    NewsAdapter newsAdapter;
    ListView newsListview;
    ArrayList<NewsInfo> newsInfoArrayList;
    TextView textTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textTitle=(TextView)findViewById(R.id.text_title);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/pricedownbl.ttf");
        textTitle.setTypeface(typeface);

        getSupportLoaderManager().initLoader(1,null,this).forceLoad();

    }


    @Override
    public Loader<ArrayList<NewsInfo>> onCreateLoader(int id, Bundle args) {
        newsListview=(ListView)findViewById(R.id.card_listView);

        return new NewsLoader(MainActivity.this);

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsInfo>> loader, ArrayList<NewsInfo> data) {
        newsListview=(ListView)findViewById(R.id.card_listView);
        newsAdapter=new NewsAdapter(getApplicationContext(),R.layout.list_item_card);
        newsInfoArrayList=data;
        for(int i=0;i<newsInfoArrayList.size();i++) {
            newsAdapter.add(newsInfoArrayList.get(i));
        }
        newsListview.setAdapter(newsAdapter);
        newsListview.setOnItemClickListener(this);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsInfo>> loader) {
    newsAdapter.setNews(new ArrayList<NewsInfo>());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        NewsInfo newsInfo=newsInfoArrayList.get(position);
       String selectedURL=newsInfo.getNewsURL().toString();
        Log.e("NEWS","CLICKED"+selectedURL);
        Toast.makeText(getApplicationContext(),"CLICKED",Toast.LENGTH_SHORT).show();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedURL));
        startActivity(browserIntent);
    }
}
