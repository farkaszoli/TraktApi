package com.example.domain.modelQuery;

import com.example.api.model.TraktIds;

public class EpisodeQuery
{
    private int season;
    private int number;
    private String title;
    private String link;
    private TraktIds ids;

    public EpisodeQuery(int season, int number, String title, String link, TraktIds ids)
    {
        this.season = season;
        this.number = number;
        this.title = title;
        this.ids = ids;
        this.link = link;
    }

    public int getSeason()
    {
        return season;
    }

    public int getNumber()
    {
        return number;
    }

    public String getTitle()
    {
        return title;
    }

    public TraktIds getIds()
    {
        return ids;
    }

    public String getLink()
    {
        return link;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + number;
        result = prime * result + season;
        return result;
    }

}
