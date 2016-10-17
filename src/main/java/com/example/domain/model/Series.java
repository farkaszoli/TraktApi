package com.example.domain.model;

import com.example.api.model.TraktSerieIds;
import com.example.api.model.TraktSeries;
import com.google.gson.annotations.SerializedName;

public class Series
{
    private String title;
    private int year;
    @SerializedName("ids")
    private TraktSerieIds serieIds;

    public Series(TraktSeries traktSeries)
    {
        this.title = traktSeries.getTitle();
        this.year = traktSeries.getYear();
        this.serieIds = traktSeries.getSerieIds();
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