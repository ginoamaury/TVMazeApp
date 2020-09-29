package com.GinoAmaury.TVMazeApp.Interfaces.Favorite;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public interface IFavoritePresenter {
    void addFav (Show show, Context c);
    void showResult (boolean result);
    void deleteFav(Show show, Context c);
    void showResultDelete(boolean result);
    void findFav(Show show,Context c);
    void showIfExist(boolean result);
    void getFavorites(Context c);
    void showResultFavorites (ArrayList<Show> shows);
}
