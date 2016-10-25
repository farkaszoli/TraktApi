package com.example.domain.domainMapper;

import com.example.api.model.TraktEpisode;
import com.example.api.model.TraktIds;
import com.example.domain.modelAttributes.Episode;
import com.example.domain.modelQuery.EpisodeQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ModelAttributesToQueryMapper
{
    public EpisodeQuery mapAttributesEpisodeToEpisodeQuery(Episode episode)
    {
        int season = Optional.ofNullable(episode).map(Episode::getSeason).orElse(0);
        int number = Optional.ofNullable(episode).map(Episode::getNumber).orElse(0);
        String title = Optional.ofNullable(episode).map(Episode::getTitle).orElse("");
        TraktIds ids = Optional.ofNullable(episode).map(Episode::getIds).orElse(null);
        String link = "www.imdb.com/title/" + ids.getImdb();

        return new EpisodeQuery(season, number, title, link, ids);
    }
}
