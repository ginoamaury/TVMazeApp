package com.GinoAmaury.TVMazeApp.Interactors;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Interfaces.Login.ILoginInteractor;
import com.GinoAmaury.TVMazeApp.Presenter.LoginPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;

public class LoginInteractor implements ILoginInteractor {

    private LoginPresenter presenter;

    public LoginInteractor(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(Context ctx, String password) {
        String response = Utility.getPreference(ctx,ctx.getResources().getString(R.string.dataSesion),ctx.getResources().getString(R.string.passwordSesion));
        presenter.showResult(response.equals(password));
    }
}
