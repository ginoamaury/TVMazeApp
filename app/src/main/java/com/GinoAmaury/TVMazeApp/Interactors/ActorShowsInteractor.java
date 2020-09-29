package com.GinoAmaury.TVMazeApp.Interactors;

import android.util.Log;

import com.GinoAmaury.TVMazeApp.API.API;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ShowsActor.ISearchActorShowsInteractor;
import com.GinoAmaury.TVMazeApp.Model.Object.Cast;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.ActorShowsPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorShowsInteractor implements ISearchActorShowsInteractor, Callback<List<Cast>> {

    ActorShowsPresenter actorShowsPresenter;

    public ActorShowsInteractor(ActorShowsPresenter actorShowsPresenter) {
        this.actorShowsPresenter = actorShowsPresenter;
    }

    @Override
    public void getShows(int id) {
        Call<List<Cast>> api = API.getApi().getShowsActor(id);
        api.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Cast>> call, Response<List<Cast>> response) {
        try{
            if (response.isSuccessful()) {
                List<Cast> res = response.body();
                ArrayList<Show> shows = new ArrayList<>();
                if(res != null) {
                    for (Cast s: res) {
                        shows.add(s.getEmbedded().getShow());
                    }
                    actorShowsPresenter.showResult(shows);
                } else {
                    actorShowsPresenter.showResult(null);
                }

            }
        }catch (Exception e){
            actorShowsPresenter.showResult(null);
        }
    }

    @Override
    public void onFailure(Call<List<Cast>> call, Throwable t) {
        actorShowsPresenter.showResult(null);
    }
}
