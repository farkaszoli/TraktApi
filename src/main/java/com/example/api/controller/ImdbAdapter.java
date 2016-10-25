package com.example.api.controller;

import com.example.api.model.TraktIds;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ImdbAdapter extends TypeAdapter<TraktIds>
{
    @Override
    public void write(JsonWriter out, TraktIds ids) throws IOException
    {
        out.beginObject();
        out.name("trakt").value(ids.getTrakt());
        out.name("tvdb").value(ids.getTvdb());
        out.name("imdb").value(ids.getImdb());
        out.name("tmdb").value(ids.getTmdb());
        out.name("tvrage").value(ids.getTvrage());
        out.name("imdbLink").value("http://www.imdb.com/title/" + ids.getImdb());
        out.endObject();
    }

    @Override
    public TraktIds read(JsonReader in) throws IOException
    {
        if (in.peek() == JsonToken.NULL)
        {
            in.nextNull();
            return null;
        }
        in.beginArray();

        int trakt = in.nextInt();
        int tvdb = in.nextInt();
        String imdb = in.nextString();
        imdb = imdb.substring(imdb.lastIndexOf("/"));
        int tmdb = in.nextInt();
        int tvrage = in.nextInt();

        in.endArray();
        return new TraktIds(trakt, tvdb, imdb, tmdb, tvrage);
    }

}