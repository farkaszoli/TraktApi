package com.example.domain.modelQuery;

import com.example.api.model.TraktSerieIds;
import com.example.api.model.TraktSeries;
import com.google.gson.annotations.SerializedName;

public class SeriesQuery
{
    private String title;
    private int year;

    @SerializedName("ids")
    private TraktSerieIds serieIds;

    public SeriesQuery(TraktSeries traktSeries)
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
