package com.GinoAmaury.TVMazeApp.Presenter;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Interactors.LoginInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Login.ILoginPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Login.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView view;
    private LoginInteractor interactor;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.interactor = new LoginInteractor(this);
    }

    @Override
    public void login(Context context, String password) {
        interactor.login(context,password);
    }

    @Override
    public void showResult(boolean result) {
        view.showResult(result);
    }
}
