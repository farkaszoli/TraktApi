package com.example.dataaccess;

import com.google.gson.annotations.SerializedName;

public class SeriesRequest
{
    private String title;
    private int year;

    @SerializedName("ids")
    private SerieIdsRequest serieIds;

    public SeriesRequest(String title, int year, SerieIdsRequest serieIds)
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

    public SerieIdsRequest getSerieIds()
    {
        return serieIds;
    }
}
