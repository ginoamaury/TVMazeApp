package com.GinoAmaury.TVMazeApp.API;

import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.People;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPI {

    // SHOWS URLS
    @GET("shows")
    Call<List<Show>> getShows(@Query("page") int page);

    @GET("search/shows")
    Call<List<Search>> getShowsSearch(@Query("q") String query);

    @GET("shows/{id}")
    Call<Show> getShow(@Path("id") int id);

    // EPISODES URLS
    @GET("shows/{id}/episodes")
    Call<List<Episode>> getEpisodes(@Path("id") int id);

    // SEARCH URLS
    @GET("search/people")
    Call<List<People>> getPersonSearch(@Query("q") String query);
}
