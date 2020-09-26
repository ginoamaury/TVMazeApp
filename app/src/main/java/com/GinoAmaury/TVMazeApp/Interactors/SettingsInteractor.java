package com.GinoAmaury.TVMazeApp.Interactors;

import android.content.Context;

import com.GinoAmaury.TVMazeApp.Interfaces.Settings.ISettingsInteractor;
import com.GinoAmaury.TVMazeApp.Presenter.SettingsPresenter;
import com.GinoAmaury.TVMazeApp.R;
import com.GinoAmaury.TVMazeApp.Util.Utility;

import static com.GinoAmaury.TVMazeApp.Util.Utility.setPreference;

public class SettingsInteractor implements ISettingsInteractor {

    SettingsPresenter settingsPresenter;

    public SettingsInteractor(SettingsPresenter settingsPresenter) {
        this.settingsPresenter = settingsPresenter;
    }

    @Override
    public void setPassword(Context context, String password) {
        boolean response = setPreference(context, context.getResources().getString(R.string.dataSesion),context.getResources().getString(R.string.passwordSesion),password);
        settingsPresenter.showResult(response);
    }

}
