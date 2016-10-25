package com.example.dataaccess;

public class SeasonRequest
{
    private final int number;
    private final IdsRequest ids;

    public SeasonRequest(int number, IdsRequest ids) {
        this.number = number;
        this.ids = ids;
    }

    public int getNumber() {
        return number;
    }

    public IdsRequest getIds() {
        return ids;
    }
}
