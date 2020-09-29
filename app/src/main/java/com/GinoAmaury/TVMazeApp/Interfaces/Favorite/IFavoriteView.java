package com.GinoAmaury.TVMazeApp.Interfaces.Favorite;

import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public interface IFavoriteView {
    void showResult (boolean result);
    void showResultDelete (boolean result);
    void showIfExist (boolean result);
    void showResultFavorites (ArrayList<Show> shows);
}
