package com.GinoAmaury.TVMazeApp.Interfaces.Shows;

import com.GinoAmaury.TVMazeApp.Model.Object.Search;

import java.util.ArrayList;

public interface IShowPresenter {
    void getShows (int page);
    void showResult (ArrayList<Search> shows);
}
