package com.example.api.model;

public class TraktEpisode
{
    private final int season;
    private final int number;
    private final String title;
    private final TraktIds ids;
    private final String link;

    public TraktEpisode(int season, int number, String title, TraktIds ids, String link)
    {
        this.season = season;
        this.number = number;
        this.title = title;
        this.ids = ids;
        this.link = link;
    }

    public TraktIds getIds()
    {
        return ids;
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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        TraktEpisode other = (TraktEpisode) obj;
        if (link == null)
        {
            if (other.link != null)
            {
                return false;
            }
        } else if (!link.equals(other.link))
        {
            return false;
        }
        if (number != other.number)
        {
            return false;
        }
        if (season != other.season)
        {
            return false;
        }
        return true;
    }
}