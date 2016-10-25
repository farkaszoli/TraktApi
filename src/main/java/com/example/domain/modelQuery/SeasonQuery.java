package com.example.domain.modelQuery;

import com.example.domain.modelAttributes.Ids;

public class SeasonQuery
{
    private final int number;
    private final Ids ids;

    public SeasonQuery(int number, Ids ids)
    {
        this.number = number;
        this.ids = ids;
    }

    public int getNumber()
    {
        return number;
    }

    public Ids getIds()
    {
        return ids;
    }
}
