package com.GinoAmaury.TVMazeApp.Interfaces.Settings;

import android.content.Context;

public interface ISettingsPresenter {
    void setPassword(Context context, String password);
    void showResult (boolean result);
}
