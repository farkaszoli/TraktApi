package com.example.orchestration.mapper;

import com.example.api.model.TraktEpisode;
import com.example.api.model.TraktIds;
import com.example.domain.model.Episode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EpisodeMapper
{
    //TODOD: java8 with optional
    // TODO: domainen belul mappeles, use attributes, request,
    // TODO: unit tests

    public Episode mapTraktEpisodeToEpisode(TraktEpisode traktEpisode)
    {
        int season = Optional.ofNullable(traktEpisode).map(TraktEpisode::getSeason).orElse(0);
        int number = Optional.ofNullable(traktEpisode).map(TraktEpisode::getNumber).orElse(0);
        String title = Optional.ofNullable(traktEpisode).map(TraktEpisode::getTitle).orElse("");
        TraktIds ids = Optional.ofNullable(traktEpisode).map(TraktEpisode::getIds).orElse(null);
        String link = "www.imdb.com/title/" + ids.getImdb();

        return new Episode(season, number, title, link, ids);
    }

    // TODO: with stream
    public List<Episode> mapList(List<TraktEpisode> traktEpisodeList)
    {
        List<Episode> episodeList = new ArrayList<>();

        for (TraktEpisode traktEpisode : traktEpisodeList)
        {
            Episode episode = mapTraktEpisodeToEpisode(traktEpisode);
            episodeList.add(episode);
        }

        return episodeList;
    }
}