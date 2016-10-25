package com.example.orchestration.mapper;

import com.example.api.model.TraktEpisode;
import com.example.api.model.TraktIds;
import com.example.domain.modelAttributes.Episode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EpisodeMapper {

    public Episode mapTraktEpisodeToEpisode(TraktEpisode traktEpisode)
    {
        int season = Optional.ofNullable(traktEpisode).map(TraktEpisode::getSeason).orElse(0);
        int number = Optional.ofNullable(traktEpisode).map(TraktEpisode::getNumber).orElse(0);
        String title = Optional.ofNullable(traktEpisode).map(TraktEpisode::getTitle).orElse("");
        TraktIds ids = Optional.ofNullable(traktEpisode).map(TraktEpisode::getIds).orElse(null);
        String link = "www.imdb.com/title/" + ids.getImdb();

        return new Episode(season, number, title, link, ids);
    }

    public List<Episode> mapList(List<TraktEpisode> traktEpisodeList)
    {
        return traktEpisodeList
                .stream()
                .map(traktEpisode -> mapTraktEpisodeToEpisode(traktEpisode))
                .collect(Collectors.toList());
    }
}