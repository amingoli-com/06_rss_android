package com.ermile.a06_rss_android.Modle;

import java.util.List;

public class RSSObject
{
    public String status;
    public Feed feed;
    public List<item> items;

    public RSSObject(String status, Feed feed, List<item> items) {
        this.status = status;
        this.feed = feed;
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<item> getItems() {
        return items;
    }

    public void setItems(List<item> items) {
        this.items = items;
    }
}