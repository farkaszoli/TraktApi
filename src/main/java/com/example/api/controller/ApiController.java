package com.example.api.controller;

import com.example.domain.Service;
import com.example.domain.model.Episode;
import com.example.domain.model.EpisodeId;
import com.example.domain.model.Season;
import com.example.domain.model.Series;
import com.example.orchestration.mapper.EpisodeMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@EnableAutoConfiguration
public class ApiController
{
    // private static final Logger LOG = (Logger) LoggerFactory.getLogger(HelloController.class);

    private final Service service;
    private Gson gson;
    String[] numberOfWatchedEpisodes;
    EpisodeMapper episodeMapper;

    @Autowired
    public ApiController(Service service, EpisodeMapper episodeMapper)
    {
        this.service = service;
        this.episodeMapper = episodeMapper;
        GsonBuilder gsonBuilder = new GsonBuilder();
        // ImdbAdapter imdbAdapter = new ImdbAdapter();
        // gsonBuilder.registerTypeAdapter(Ids.class, imdbAdapter);
        this.gson = gsonBuilder.create();
    }

    public int getSeasonNumber(String serieName)
    {
        List<Season> seasonNumber = service.getSeasons(serieName);
        return (seasonNumber.size()) - 1;
    }

    @RequestMapping("/episodes")
    @ResponseBody
    public String getEpisodes(@RequestParam(required = true) String serieName,
            @RequestParam(required = false) Integer seasonNum,
            @RequestParam(required = false) String watchedEpisode)
    {
        if (watchedEpisode == null)
        {
            if (seasonNum == null)
            {
                // serieName
                Series series = service.getSeries(serieName);
                return serializeFromSeries(series);
            }

            // seasonNum
            List<Episode> seasonEpisodes = episodeMapper.mapList(service.getEpisodes(serieName, seasonNum));
            return serializeFromEpisodeList(seasonEpisodes);
        }
        // all episodes without watchedEpisodes
        numberOfWatchedEpisodes = watchedEpisode.split(",");
        List<Episode> episodes = new ArrayList<>();
        List<Episode> watchedEpisodes = new ArrayList<>();

        for (int i = 0; i < numberOfWatchedEpisodes.length; i++)
        {
            EpisodeId episodeId = parseEpisodeId(serieName, i);
            Episode episode = episodeMapper.mapTraktEpisodeToEpisode(service.getEpisode(episodeId));
            watchedEpisodes.add(episode);
        }

        // all episodes without watchedEpisodes
        if (seasonNum == null)
        {
            for (Season season : service.getSeasons(serieName))
            {
                for (Episode episode : episodeMapper.mapList(service.getEpisodes(serieName, season.getNumber())))
                {

                    if (!watchedEpisodes.contains(episode))
                    {
                        episodes.add(episode);
                    }
                }
            }
            return serializeFromEpisodeList(episodes);
        }

        // season without watchedEpisodes
        for (Episode episode : episodeMapper.mapList(service.getEpisodes(serieName, seasonNum)))
        {
            if (!watchedEpisodes.contains(episode))
            {
                episodes.add(episode);
            }
        }
        return serializeFromEpisodeList(episodes);
    }

    private EpisodeId parseEpisodeId(String serieName, int i)
    {
        String numberOfWatchedEp = numberOfWatchedEpisodes[i];
        int seasonNameStartIndex = numberOfWatchedEp.lastIndexOf("s") + 1;
        int episodeNameStartIndex = numberOfWatchedEp.lastIndexOf("e");
        int seasonNumber = Integer.parseInt(numberOfWatchedEp.substring(seasonNameStartIndex, episodeNameStartIndex));
        int episodeNumber = Integer.parseInt(numberOfWatchedEp.substring(episodeNameStartIndex + 1));
        return new EpisodeId(serieName, seasonNumber, episodeNumber);
    }

    private String serializeFromSeries(Series series)
    {
        return gson.toJson(series);
    }

    private String serializeFromEpisodeList(List<Episode> episodes)
    {
        return gson.toJson(episodes);
    }
}