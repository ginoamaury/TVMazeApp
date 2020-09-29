package com.GinoAmaury.TVMazeApp.Interactors;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Interfaces.Favorite.IFavoriteInteractor;
import com.GinoAmaury.TVMazeApp.Model.DB.SQLConnection;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.FavoritePresenter;

public class FavoriteInteractor implements IFavoriteInteractor {

    FavoritePresenter favoritePresenter;

    public FavoriteInteractor(FavoritePresenter favoritePresenter) {
        this.favoritePresenter = favoritePresenter;
    }

    @Override
    public void addFav(Show show, Context c) {
        SQLConnection helper = SQLConnection.getInstance(c);
        favoritePresenter.showResult(helper.addFav(show));
    }

    @Override
    public void deleteFav(Show show, Context c) {
        SQLConnection helper = SQLConnection.getInstance(c);
        favoritePresenter.showResultDelete(helper.deleteFav(show));
    }

    @Override
    public void findFav(Show show, Context c) {
        SQLConnection helper = SQLConnection.getInstance(c);
        favoritePresenter.showIfExist(helper.ifExist(show));
    }

    @Override
    public void getFavorites(Context c) {
        SQLConnection helper = SQLConnection.getInstance(c);
        favoritePresenter.showResultFavorites(helper.getFavorites());
    }
}
