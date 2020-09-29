package com.GinoAmaury.TVMazeApp.Presenter;

import com.GinoAmaury.TVMazeApp.Interactors.SearchActorInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ISearchActorPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ISearchActorView;
import com.GinoAmaury.TVMazeApp.Model.Object.Person;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public class SearchActorPresenter implements ISearchActorPresenter {

    SearchActorInteractor searchActorInteractor;
    ISearchActorView searchActorView;

    public SearchActorPresenter(ISearchActorView searchActorView) {
        this.searchActorView = searchActorView;
        searchActorInteractor = new SearchActorInteractor(this);
    }

    @Override
    public void getActors(String name) {
        searchActorInteractor.getActors(name);
    }

    @Override
    public void showResult(ArrayList<Person> actors) {
        searchActorView.showResult(actors);
    }

}
