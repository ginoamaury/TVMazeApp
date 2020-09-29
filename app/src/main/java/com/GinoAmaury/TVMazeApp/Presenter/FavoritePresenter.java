package com.GinoAmaury.TVMazeApp.Presenter;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Interactors.FavoriteInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoritePresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoriteView;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;

import java.util.ArrayList;

public class FavoritePresenter implements IFavoritePresenter {

    FavoriteInteractor favoriteInteractor;
    IFavoriteView favoriteView;

    public FavoritePresenter(IFavoriteView favoriteView) {
        this.favoriteView = favoriteView;
        favoriteInteractor = new FavoriteInteractor(this);
    }

    @Override
    public void addFav(Show show, Context c) {
        favoriteInteractor.addFav(show, c);
    }

    @Override
    public void showResult(boolean result) {
        favoriteView.showResult(result);
    }

    @Override
    public void deleteFav(Show show, Context c) {
        favoriteInteractor.deleteFav(show,c);
    }

    @Override
    public void showResultDelete(boolean result) {
        favoriteView.showResultDelete(result);
    }

    @Override
    public void findFav(Show show, Context c) {
        favoriteInteractor.findFav(show,c);
    }

    @Override
    public void showIfExist(boolean result) {
        favoriteView.showIfExist(result);
    }

    @Override
    public void getFavorites(Context c) {
        favoriteInteractor.getFavorites(c);
    }

    @Override
    public void showResultFavorites(ArrayList<Show> shows) {
        favoriteView.showResultFavorites(shows);
    }
}
