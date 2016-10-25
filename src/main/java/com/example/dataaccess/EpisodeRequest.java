package com.example.dataaccess;

import com.example.api.model.TraktIds;

public class EpisodeRequest
{
    private final int season;
    private final int number;
    private final String title;
    private final TraktIds ids;
    private final String link;

    public EpisodeRequest(int season, int number, String title, TraktIds ids, String link) {
        this.season = season;
        this.number = number;
        this.title = title;
        this.ids = ids;
        this.link = link;
    }

    public int getSeason() {
        return season;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public TraktIds getIds() {
        return ids;
    }

    public String getLink() {
        return link;
    }
}
