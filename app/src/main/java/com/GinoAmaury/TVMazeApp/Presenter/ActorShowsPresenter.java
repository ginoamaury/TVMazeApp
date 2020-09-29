package com.GinoAmaury.TVMazeApp.Presenter;

import com.GinoAmaury.TVMazeApp.Interactors.ActorShowsInteractor;
import com.GinoAmaury.TVMazeApp.Interactors.SearchShowInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ShowsActor.ISearchActorShowsPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ShowsActor.ISearchActorShowsView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public class ActorShowsPresenter implements ISearchActorShowsPresenter {

    ActorShowsInteractor actorShowsInteractor;
    ISearchActorShowsView searchActorShowsView;

    public ActorShowsPresenter(ISearchActorShowsView searchActorShowsView) {
        this.searchActorShowsView = searchActorShowsView;
        actorShowsInteractor = new ActorShowsInteractor(this);
    }

    @Override
    public void getShows(int id) {
        actorShowsInteractor.getShows(id);
    }

    @Override
    public void showResult(ArrayList<Show> shows) {
        searchActorShowsView.showResult(shows);
    }
}
