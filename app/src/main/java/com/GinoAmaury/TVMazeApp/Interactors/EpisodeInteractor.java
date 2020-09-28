package com.GinoAmaury.TVMazeApp.Interactors;

import com.GinoAmaury.TVMazeApp.API.API;
import com.GinoAmaury.TVMazeApp.Interfaces.Episodes.IEpisodesInteractor;
import com.GinoAmaury.TVMazeApp.Model.Object.Episode;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Presenter.EpisodePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeInteractor implements IEpisodesInteractor, Callback<List<Episode>> {

    EpisodePresenter episodePresenter;

    public EpisodeInteractor(EpisodePresenter episodePresenter) {
        this.episodePresenter = episodePresenter;
    }

    @Override
    public void getEpisodes(int show) {
        Call<List<Episode>> api = API.getApi().getEpisodes(show);
        api.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
        try{
            if(response.isSuccessful()){
                ArrayList<Episode> episodes  = (ArrayList<Episode>) response.body();
                if(!episodes.isEmpty()){
                    episodePresenter.showResult(episodes);
                }else{
                    episodePresenter.showResult(null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            episodePresenter.showResult(null);
        }
    }

    @Override
    public void onFailure(Call<List<Episode>> call, Throwable t) {
        episodePresenter.showResult(null);
    }
}
