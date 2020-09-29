package com.GinoAmaury.TVMazeApp.Presenter;

import com.GinoAmaury.TVMazeApp.Interactors.SearchShowInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Show.ISearchShowPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Show.ISearchShowView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public class SearchShowPresenter implements ISearchShowPresenter {

    ISearchShowView searchShowView;
    SearchShowInteractor searchShowInteractor;

    public SearchShowPresenter(ISearchShowView searchShowView) {
        this.searchShowView = searchShowView;
        searchShowInteractor = new SearchShowInteractor(this);
    }

    @Override
    public void getShows(String name) {
        searchShowInteractor.getShows(name);
    }

    @Override
    public void showResult(ArrayList<Show> shows) {
        searchShowView.showResult(shows);
    }
}
