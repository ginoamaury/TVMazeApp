package com.GinoAmaury.TVMazeApp.Interfaces.Favorite;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Model.Object.Show;

public interface IFavoriteInteractor {
    void addFav(Show show, Context c);
    void deleteFav(Show show,Context c);
    void findFav(Show show,Context c);
    void getFavorites(Context c);
}
