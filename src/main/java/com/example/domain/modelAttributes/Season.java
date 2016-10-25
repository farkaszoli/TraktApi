package com.example.domain.modelAttributes;

public class Season
{
    private final int number;
    private final Ids ids;

    public Season(int number, Ids ids)
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