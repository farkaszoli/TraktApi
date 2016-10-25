package com.example.domain.modelAttributes;

import com.example.api.model.TraktIds;

public class Ids
{
    private final int trakt;
    private int tvdb;
    private String imdb;
    private int tmdb;
    private int tvrage;

    public Ids(TraktIds traktIds)
    {
        this.trakt = traktIds.getTrakt();
        this.tvdb = traktIds.getTvdb();
        this.imdb = traktIds.getImdb();
        this.tmdb = traktIds.getTmdb();
        this.tvrage = traktIds.getTvrage();
    }

    public int getTrakt()
    {
        return trakt;
    }

    public int getTvdb()
    {
        return tvdb;
    }

    public String getImdb()
    {
        return imdb;
    }

    public int getTmdb()
    {
        return tmdb;
    }

    public int getTvrage()
    {
        return tvrage;
    }
}
