package com.example.api.model;

import com.google.gson.annotations.SerializedName;

public class TraktSeries
{
    private String title;
    private int year;
    @SerializedName("ids")
    private TraktSerieIds serieIds;

    public TraktSeries(String title, int year, TraktSerieIds serieIds)
    {
        this.title = title;
        this.year = year;
        this.serieIds = serieIds;
    }

    public String getTitle()
    {
        return title;
    }

    public int getYear()
    {
        return year;
    }

    public TraktSerieIds getSerieIds()
    {
        return serieIds;
    }
}