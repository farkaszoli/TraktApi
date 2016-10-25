package com.domain.domainMapper;

import com.example.api.model.TraktIds;
import com.example.domain.domainMapper.ModelAttributesToQueryMapper;
import com.example.domain.modelAttributes.Episode;
import com.example.domain.modelQuery.EpisodeQuery;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test(groups = { "unit" })
public class ModelAttributesToQueryMapperTest
{
    private ModelAttributesToQueryMapper modelAttributesToQueryMapper;
    private TraktIds trackId = givenATraktId();
    private EpisodeQuery expectedChuck;
    private Episode chuck;

    @BeforeTest
    public void init()
    {
        modelAttributesToQueryMapper = new ModelAttributesToQueryMapper();
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
        expectedChuck = modelAttributesToQueryMapper.mapAttributesEpisodeToEpisodeQuery(chuck);
    }

    private void thenEpisodeShouldBeMap()
    {
        Assert.assertEquals(chuck.getTitle(), expectedChuck.getTitle());
        Assert.assertEquals(chuck.getSeason(), expectedChuck.getSeason());
        Assert.assertEquals(chuck.getNumber(), expectedChuck.getNumber());
        Assert.assertEquals(chuck.getIds(), expectedChuck.getIds());
    }
}
