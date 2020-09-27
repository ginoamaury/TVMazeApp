package com.GinoAmaury.TVMazeApp.Interactors;

import com.GinoAmaury.TVMazeApp.API.API;
import com.GinoAmaury.TVMazeApp.Interfaces.Shows.IShowInteractor;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Presenter.ShowPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShowInteractor implements IShowInteractor, Callback<List<Search>> {

    ShowPresenter showPresenter;

    public ShowInteractor(ShowPresenter showPresenter) {
        this.showPresenter = showPresenter;
    }

    @Override
    public void getShows(int page) {
        Call<List<Search>> api = API.getApi().getShows(page);
        api.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
        try{
            if(response.isSuccessful()){
                ArrayList <Search>  show  = (ArrayList<Search>) response.body();
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
    public void onFailure(Call<List<Search>> call, Throwable t) {
        showPresenter.showResult(null);
    }
}
