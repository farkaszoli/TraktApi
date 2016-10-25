package com.example.api.model;

public class TraktIds
{
    private final int trakt;
    private final int tvdb;
    private final String imdb;
    private final int tmdb;
    private final int tvrage;

    public TraktIds(int trakt, int tvdb, String imdb, int tmdb, int tvrage)
    {
        this.trakt = trakt;
        this.tvdb = tvdb;
        this.imdb = imdb;
        this.tmdb = tmdb;
        this.tvrage = tvrage;
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