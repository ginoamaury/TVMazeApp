package com.GinoAmaury.TVMazeApp.Interactors;

import com.GinoAmaury.TVMazeApp.API.API;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.ISearchShowInteractor;
import com.GinoAmaury.TVMazeApp.Interfaces.Shows.IShowInteractor;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Presenter.SearchShowPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchShowInteractor implements ISearchShowInteractor , Callback<List<Search>> {

    SearchShowPresenter presenter;

    public SearchShowInteractor(SearchShowPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getShows(String name) {
        Call<List<Search>> api = API.getApi().getShowsSearch(name);
        api.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
        try{
            if(response.isSuccessful()){
                ArrayList<Search> show  = (ArrayList<Search>) response.body();
                if(!show.isEmpty()){
                    presenter.showResult(show);
                }else{
                    presenter.showResult(null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            presenter.showResult(null);
        }
    }

    @Override
    public void onFailure(Call<List<Search>> call, Throwable t) {
        presenter.showResult(null);
    }
}
