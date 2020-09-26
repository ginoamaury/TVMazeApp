package com.GinoAmaury.TVMazeApp.Interfaces.Login;

import android.content.Context;

public interface ILoginPresenter {

    void login(Context context, String password);
    void showResult (boolean result);

}
