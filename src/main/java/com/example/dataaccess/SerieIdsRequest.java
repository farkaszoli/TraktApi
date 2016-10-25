package com.example.dataaccess;


public class SerieIdsRequest extends IdsRequest
{
    private String slug;

    public SerieIdsRequest(int trakt, int tvdb, String imdb, int tmdb, int tvrage, String slug)
    {
        super(trakt, tvdb, imdb, tmdb, tvrage);
        this.slug = slug;
    }

    public String getSlug()
    {
        return slug;
    }
}
