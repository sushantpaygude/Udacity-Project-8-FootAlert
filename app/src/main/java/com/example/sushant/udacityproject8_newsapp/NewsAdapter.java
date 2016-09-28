package com.example.sushant.udacityproject8_newsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 14/9/16.
 */
public class NewsAdapter extends ArrayAdapter<NewsInfo> {
    public NewsAdapter(Context context, int resource) {
        super(context, resource);
    }

    List<NewsInfo> cardList=new ArrayList<NewsInfo>();

    static class CardViewHolder{
        ImageView newsThumbnailView;
        TextView newsTitleView;
        TextView newsURLView;
        TextView newsAuthorView;

    }

    @Override
    public void add(NewsInfo object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public NewsInfo getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        CardViewHolder cardViewHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_item_card,parent,false);
            cardViewHolder=new CardViewHolder();
            cardViewHolder.newsTitleView=(TextView)row.findViewById(R.id.news_title);
            cardViewHolder.newsThumbnailView=(ImageView)row.findViewById(R.id.news_thumbnail);
            cardViewHolder.newsAuthorView=(TextView)row.findViewById(R.id.news_author);
            row.setTag(cardViewHolder);
        }
        else{
            cardViewHolder=(CardViewHolder)row.getTag();
        }

        NewsInfo newsInfo=getItem(position);
        cardViewHolder.newsTitleView.setText(newsInfo.getNewsTitle());
        cardViewHolder.newsAuthorView.setText(newsInfo.getNewsAuthor());
        new LoadImageTask(cardViewHolder.newsThumbnailView).execute(newsInfo.getNewsThumbnail());

        return row;

    }

    public void setNews(ArrayList<NewsInfo> newsData)
    {
        cardList.addAll(newsData);
        notifyDataSetChanged();
    }
}
