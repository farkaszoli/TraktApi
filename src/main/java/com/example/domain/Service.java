package com.example.domain;

import com.example.api.model.TraktEpisode;
import com.example.domain.model.EpisodeId;
import com.example.domain.model.Season;
import com.example.domain.model.Series;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Component
public class Service
{
    private static final Logger LOG = LoggerFactory.getLogger(Service.class);

    private static final String CLIENT_ID = "a386318ad9ade488fd7c2a697aaaf17f8b9edba2ffe104a4ada2e8b9108c38a7";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TRAKT_API_KEY = "trakt-api-key";
    private static final String TRAKT_API_VERSION = "trakt-api-version";
    private static final String TRAKT_EPISODES_ENDPOINT = "https://api-v2launch.trakt.tv/shows/";
    private static final List<Season> EMPTY_SEASON_LIST= Collections.emptyList();
    private static final List<TraktEpisode> EMPTY_TRAKTEPISODE_LIST= Collections.emptyList();

    private final Gson gson;

    private CloseableHttpClient client;

    public Service()
    {
        client = HttpClients.createDefault();
        this.gson = new Gson();
    }

    public List<Season> getSeasons(String serie)
    {
        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + serie + "/seasons/");
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);
        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToSeasonList(jsonResponse);

        } catch (IOException e)
        {
            LOG.error("getSeasons IOException printStackTrace", e);
            return EMPTY_SEASON_LIST;
        }
    }

    public List<TraktEpisode> getEpisodes(String serie, int season)
    {
        if (season < 0)
        {
            throw new IllegalArgumentException("Season less than 1!");
        }

        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + serie + "/seasons/" + season + "/episodes/");
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);

        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToEpisodeList(jsonResponse);
        } catch (IOException e)
        {
            LOG.error("getEpisodes IOException printStackTrace", e);
            return EMPTY_TRAKTEPISODE_LIST;
        }
    }

    public TraktEpisode getEpisode(EpisodeId episodeId)
    {
        if (episodeId.getSeason() < 0 || episodeId.getEpisode() < 0)
        {
            throw new IllegalArgumentException("Season or episode less than 1!");
        }

        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + episodeId.getSerieName() + "/seasons/" + episodeId.getSeason() + "/episodes/" + episodeId.getEpisode());
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);

        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToTraktEpisode(jsonResponse);
        } catch (IOException e)
        {
            LOG.error("getEpisode IOException printStackTrace", e);
            return null;
        }
    }

    public Series getSeries(String serie)
    {
        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + serie);
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);

        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToSerie(jsonResponse);
        } catch (IOException e)
        {
            LOG.error("getSeries IOException printStackTrace", e);
            return null;
        }
    }

    private Series deserializeToSerie(String json) throws IOException
    {
        return gson.fromJson(json, Series.class);
    }

    private TraktEpisode deserializeToTraktEpisode(String json) throws IOException
    {
        return gson.fromJson(json, TraktEpisode.class);
    }

    private List<Season> deserializeToSeasonList(String json) throws IOException
    {
        Type seasonListType = new TypeToken<List<Season>>(){}.getType();
        return gson.fromJson(json, seasonListType);
    }

    private List<TraktEpisode> deserializeToEpisodeList(String json) throws IOException
    {
        Type episodeListType = new TypeToken<List<TraktEpisode>>(){}.getType();
        return gson.fromJson(json, episodeListType);
    }
}