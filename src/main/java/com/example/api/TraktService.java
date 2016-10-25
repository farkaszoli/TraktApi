package com.example.api;

import com.example.api.model.TraktEpisode;
import com.example.api.model.TraktSeason;
import com.example.api.model.TraktSeries;
import com.google.gson.Gson;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TraktService
{
    private static final Logger LOG = LoggerFactory.getLogger(TraktService.class);

    private static final String CLIENT_ID = "a386318ad9ade488fd7c2a697aaaf17f8b9edba2ffe104a4ada2e8b9108c38a7";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TRAKT_API_KEY = "trakt-api-key";
    private static final String TRAKT_API_VERSION = "trakt-api-version";
    private static final String TRAKT_EPISODES_ENDPOINT = "https://api-v2launch.trakt.tv/shows/";
    private static final TraktEpisode[] emptyTraktEpisode = new TraktEpisode[0];

    private final Gson gson;

    private CloseableHttpClient client;

    public TraktService()
    {
        client = HttpClients.createDefault();
        this.gson = new Gson();
    }

    public TraktSeason[] getSeasons(String series)
    {
        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + series + "/seasons/");
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);
        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToTraktSeasonArray(jsonResponse);

        } catch (IOException e)
        {
            LOG.error("getSeasons IOException printStackTrace", e);
            return new TraktSeason[0];
        }
    }

    public TraktEpisode[] getEpisodes(String serie, int season)
    {
        if (season < 1)
        {
            return emptyTraktEpisode;
        }

        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + serie + "/seasons/" + season + "/episodes/");
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);

        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToTraktEpisodeArray(jsonResponse);
        } catch (IOException e)
        {
            LOG.error("getEpisodes IOException printStackTrace", e);
            return emptyTraktEpisode;
        }
    }

    public TraktEpisode getEpisode(String serie, int season, int episode)
    {
        if (season < 1 || episode < 1)
        {
            return null;
        }

        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + serie + "/seasons/" + season + "/episodes/" + episode);
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

    public TraktSeries getSeries(String serie)
    {
        HttpGet httpGet = new HttpGet(TRAKT_EPISODES_ENDPOINT + serie);
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        httpGet.addHeader(TRAKT_API_VERSION, "2");
        httpGet.addHeader(TRAKT_API_KEY, CLIENT_ID);

        try (CloseableHttpResponse response = client.execute(httpGet))
        {
            String jsonResponse = new BasicResponseHandler().handleResponse(response);
            return deserializeToTraktSerie(jsonResponse);
        } catch (IOException e)
        {
            LOG.error("getSeries IOException printStackTrace", e);
            return null;
        }
    }

    private TraktSeries deserializeToTraktSerie(String json) throws HttpResponseException, IOException
    {
        return gson.fromJson(json, TraktSeries.class);
    }

    private TraktEpisode deserializeToTraktEpisode(String json) throws HttpResponseException, IOException
    {
        return gson.fromJson(json, TraktEpisode.class);
    }

    private TraktSeason[] deserializeToTraktSeasonArray(String json) throws HttpResponseException, IOException
    {
        return gson.fromJson(json, TraktSeason[].class);
    }

    private TraktEpisode[] deserializeToTraktEpisodeArray(String json) throws HttpResponseException, IOException
    {
        return gson.fromJson(json, TraktEpisode[].class);
    }

}