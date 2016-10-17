package com.utils.mockbuilders;

import com.example.api.model.TraktEpisode;
import com.example.domain.Service;
import com.example.domain.model.EpisodeId;
import com.example.domain.model.Season;
import com.example.domain.model.Series;
import org.easymock.EasyMock;

import java.util.List;

public class ServiceMockBuilder
{
    private List<Season> seasons;
    private TraktEpisode traktEpisode;
    private List<TraktEpisode> traktEpisodes;
    private Series series;

    private ServiceMockBuilder()
    {
    }

    public static ServiceMockBuilder aServiceMockBuilder()
    {
        return new ServiceMockBuilder();
    }

    public ServiceMockBuilder withSeasonList(List<Season> seasonList)
    {
        this.seasons = seasonList;
        return this;
    }

    public ServiceMockBuilder withTraktEpisode(TraktEpisode traktEpisode)
    {
        this.traktEpisode = traktEpisode;
        return this;
    }

    public ServiceMockBuilder withTraktEpisodes(List<TraktEpisode> traktEpisodes)
    {
        this.traktEpisodes = traktEpisodes;
        return this;
    }

    public ServiceMockBuilder withSeries(Series series)
    {
        this.series = series;
        return this;
    }


    public Service build()
    {
        Service service = EasyMock.createMock(Service.class);

        EasyMock.expect(service.getSeries(EasyMock.anyString())).andStubReturn(series);
        EasyMock.expect(service.getSeasons(EasyMock.anyString())).andStubReturn(seasons);
        EasyMock.expect(service.getEpisodes(EasyMock.anyString(), EasyMock.anyInt())).andStubReturn(traktEpisodes);
        EasyMock.expect(service.getEpisode(EasyMock.anyObject(EpisodeId.class))).andStubReturn(traktEpisode);

        EasyMock.replay(service);

        return service;
    }
}