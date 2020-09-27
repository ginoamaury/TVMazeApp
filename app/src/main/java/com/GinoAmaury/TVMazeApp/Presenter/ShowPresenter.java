package com.GinoAmaury.TVMazeApp.Presenter;

import com.GinoAmaury.TVMazeApp.Interactors.ShowInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Shows.IShowPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Shows.IShowView;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;

import java.util.ArrayList;

public class ShowPresenter implements IShowPresenter {

    ShowInteractor showInteractor;
    IShowView showView;

    public ShowPresenter(IShowView showView) {
        this.showView = showView;
        this.showInteractor = new ShowInteractor(this);
    }

    @Override
    public void getShows(int page) {
        showInteractor.getShows(page);
    }

    @Override
    public void showResult(ArrayList<Search> shows) {
        showView.showResult(shows);
    }
}
