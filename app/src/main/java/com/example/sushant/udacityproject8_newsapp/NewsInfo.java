package com.example.sushant.udacityproject8_newsapp;

/**
 * Created by sushant on 14/9/16.
 */
public class NewsInfo {
    public String newsTitle;
    public String newsThumbnail;
    public String newsURL;

    public NewsInfo(String newsTitle, String newsThumbnail, String newsURL) {
        this.newsTitle = newsTitle;
        this.newsThumbnail = newsThumbnail;
        this.newsURL = newsURL;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsThumbnail() {
        return newsThumbnail;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public void setNewsThumbnail(String newsThumbnail) {
        this.newsThumbnail = newsThumbnail;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }
}
