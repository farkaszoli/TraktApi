package com.example.domain.modelAttributes;

public class EpisodeId
{
    private final String serieName;
    private final int season;
    private final int episode;

    public EpisodeId(String serieName, int season, int episode)
    {
        this.serieName = serieName;
        this.season = season;
        this.episode = episode;
    }

    public int getSeason()
    {
        return season;
    }

    public int getEpisode()
    {
        return episode;
    }

    public String getSerieName()
    {
        return serieName;
    }
}