package com.GinoAmaury.TVMazeApp.Presenter;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Interactors.SettingsInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Settings.ISettingsPresenter;
import com.GinoAmaury.TVMazeApp.Interfaces.Settings.ISettingsView;

public class SettingsPresenter implements ISettingsPresenter {

    SettingsInteractor interactor;
    ISettingsView view;

    public SettingsPresenter(ISettingsView view) {
        this.view = view;
        interactor = new SettingsInteractor(this);
    }

    @Override
    public void setPassword(Context context, String password) {
        interactor.setPassword(context,password);
    }

    @Override
    public void showResult(boolean result) {
        view.showResult(result);
    }
}
