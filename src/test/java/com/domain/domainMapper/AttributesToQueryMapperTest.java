package com.domain.domainMapper;

import com.example.api.model.TraktIds;
import com.example.domain.domainMapper.AttributesToQueryMapper;
import com.example.domain.modelAttributes.Episode;
import com.example.domain.modelQuery.EpisodeQuery;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = { "unit" })
public class AttributesToQueryMapperTest
{
    private AttributesToQueryMapper attributesToQueryMapper;
    private TraktIds trackId = givenATraktId();
    private EpisodeQuery expectedChuck;
    private Episode chuck;

    @BeforeTest
    public void init()
    {
        attributesToQueryMapper = new AttributesToQueryMapper();
    }

    @Test
    public void shouldmapTraktEpisodeToEpisode()
    {
        givenATraktId();
        givenAnEpisode();
        givenAnExceptedEpisode();
        whenMapCalled();
        thenEpisodeShouldBeMap();
    }

    private TraktIds givenATraktId()
    {
        TraktIds trackId = new TraktIds(1, 1, "imdb", 3, 6);
        return trackId;
    }

    private void givenAnEpisode()
    {
        chuck = new Episode(1, 2, "chuck", "chuck", trackId);
    }

    private void givenAnExceptedEpisode()
    {
        expectedChuck = new EpisodeQuery(1, 2, "chuck", "chuck", trackId);
    }

    private void whenMapCalled()
    {
        expectedChuck = attributesToQueryMapper.mapAttributesEpisodeToEpisodeQuery(chuck);
    }

    private void thenEpisodeShouldBeMap()
    {
        Assert.assertEquals(chuck.getTitle(), expectedChuck.getTitle());
        Assert.assertEquals(chuck.getSeason(), expectedChuck.getSeason());
        Assert.assertEquals(chuck.getNumber(), expectedChuck.getNumber());
        Assert.assertEquals(chuck.getIds(), expectedChuck.getIds());
    }
}
