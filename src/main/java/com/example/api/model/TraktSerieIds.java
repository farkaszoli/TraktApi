package com.example.api.model;

public class TraktSerieIds extends TraktIds
{
    private String slug;

    public TraktSerieIds(int trakt, int tvdb, String imdb, int tmdb, int tvrage, String slug)
    {
        super(trakt, tvdb, imdb, tmdb, tvrage);
        this.slug = slug;
    }

    public String getSlug()
    {
        return slug;
    }
}