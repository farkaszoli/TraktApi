package com.example.service.mappers;

import com.example.api.model.TraktIds;
import com.example.dataaccess.EpisodeRequest;
import com.example.domain.modelAttributes.Episode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Mapper
{
    public EpisodeRequest mapEpisodeToEpisodeRequest(Episode episode)
    {
        int season = Optional.ofNullable(episode).map(Episode::getSeason).orElse(0);
        int number = Optional.ofNullable(episode).map(Episode::getNumber).orElse(0);
        String title = Optional.ofNullable(episode).map(Episode::getTitle).orElse("");
        TraktIds idsRequest = Optional.ofNullable(episode).map(Episode::getIds).orElse(null);
        String link = "www.imdb.com/title/" + idsRequest.getImdb();

        return new EpisodeRequest(season, number, title, idsRequest, link);
    }

    public List<EpisodeRequest> mapList(List<Episode> episodeList)
    {
        return episodeList.stream().map(episode -> mapEpisodeToEpisodeRequest(episode)).collect(Collectors.toList());
    }
}
