package com.GinoAmaury.TVMazeApp.Presenter;

import com.GinoAmaury.TVMazeApp.Interactors.SearchShowInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.ISearchShowPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.ISearchShowView;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;

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
    public void showResult(ArrayList<Search> shows) {
        searchShowView.showResult(shows);
    }
}
