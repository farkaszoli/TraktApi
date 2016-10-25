package com.example.mapper;

import com.example.api.model.TraktEpisode;
import com.example.api.model.TraktIds;
import com.example.domain.modelAttributes.Episode;
import com.example.orchestration.mapper.EpisodeMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = { "unit" })
public class EpisodeMapperTest
{
    private List<TraktEpisode> traktEpisodeList;
    private List<Episode> actualEpisodes;
    private List<Episode> exceptedEpisodes;
    private EpisodeMapper episodeMapper;
    private TraktEpisode arrowTraktEpisode;
    private TraktIds trackId = givenATraktId();
    private Episode expectedArrow;
    private Episode actualArrow;

    @BeforeTest
    public void init()
    {
        episodeMapper = new EpisodeMapper();
    }

    @Test
    public void shouldMapTracktEpisodeList()
    {
        givenATracktEpisodeList();
        givenAnExceptedEpisodeList();
        whenMapCalled();
        thenTraktEpisodeListShouldBeMap();
    }

    @Test
    public void shouldmapTraktEpisodeToEpisode()
    {
        givenATracktEpisode();
        givenAnExceptedEpisode();
        whenMapTraktEpisodeToEpisodeCalled();
        thenTraktEpisodeShouldBeMap();
    }

    private void givenATracktEpisodeList()
    {
        List<TraktEpisode> episodes = new ArrayList<>();

        TraktIds trackId = givenATraktId();

        TraktEpisode arrow = new TraktEpisode(1, 2, "pilot", trackId, "arrow");
        TraktEpisode chuck = new TraktEpisode(1, 2, "chuck", trackId, "chuck");
        TraktEpisode brakingBad = new TraktEpisode(1, 2, "brakingBad", trackId, "brakingBad");

        episodes.add(arrow);
        episodes.add(chuck);
        episodes.add(brakingBad);

        traktEpisodeList = episodes;
    }

    private TraktIds givenATraktId()
    {
        TraktIds trackId = new TraktIds(1, 1, "imdb", 3, 6);
        return trackId;
    }

    private void givenAnExceptedEpisodeList()
    {
        List<Episode> episodes = new ArrayList<>();

        TraktIds trackId = givenATraktId();

        Episode arrow = new Episode(1, 2, "pilot", "arrow", trackId);
        Episode chuck = new Episode(1, 2, "chuck", "chuck", trackId);
        Episode brakingBad = new Episode(1, 2, "brakingBad", "brakingBad", trackId);

        episodes.add(arrow);
        episodes.add(chuck);
        episodes.add(brakingBad);

        exceptedEpisodes = episodes;
    }

    private void whenMapCalled()
    {
        actualEpisodes = episodeMapper.mapList(traktEpisodeList);
    }

    private void thenTraktEpisodeListShouldBeMap()
    {
        Assert.assertEquals(actualEpisodes.size(), exceptedEpisodes.size());
    }

    private void givenAnExceptedEpisode()
    {
        expectedArrow = new Episode(1, 2, "pilot", "arrow_link", trackId);
    }

    private void givenATracktEpisode()
    {
        arrowTraktEpisode = new TraktEpisode(1, 2, "pilot", trackId, "arrow_link");
    }

    private void whenMapTraktEpisodeToEpisodeCalled()
    {
        actualArrow = episodeMapper.mapTraktEpisodeToEpisode(arrowTraktEpisode);
    }

    private void thenTraktEpisodeShouldBeMap()
    {
        Assert.assertEquals(actualArrow.getClass(), expectedArrow.getClass());
    }
}