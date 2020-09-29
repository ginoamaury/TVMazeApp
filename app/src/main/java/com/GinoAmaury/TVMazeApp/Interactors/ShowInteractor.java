package com.GinoAmaury.TVMazeApp.Interactors;

import com.GinoAmaury.TVMazeApp.API.API;
import com.GinoAmaury.TVMazeApp.Interfaces.Shows.IShowInteractor;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.ShowPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShowInteractor implements IShowInteractor, Callback<List<Show>> {

    ShowPresenter showPresenter;

    public ShowInteractor(ShowPresenter showPresenter) {
        this.showPresenter = showPresenter;
    }

    @Override
    public void getShows(int page) {
        Call<List<Show>> api = API.getApi().getShows(page);
        api.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
        try{
            if(response.isSuccessful()){
                ArrayList <Show>  show  = (ArrayList<Show>) response.body();
                if(!show.isEmpty()){
                    showPresenter.showResult(show);
                }else{
                    showPresenter.showResult(null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            showPresenter.showResult(null);
        }
    }

    @Override
    public void onFailure(Call<List<Show>> call, Throwable t) {
        showPresenter.showResult(null);
    }
}
