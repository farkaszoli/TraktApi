package com.example.api.model;

public class TraktSeason
{
    private final int number;
    private final TraktIds ids;

    public TraktSeason(int number, TraktIds ids)
    {
        this.number = number;
        this.ids = ids;
    }

    public int getNumber()
    {
        return number;
    }

    public TraktIds getIds()
    {
        return ids;
    }
}