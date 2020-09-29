package com.GinoAmaury.TVMazeApp.Interactors;

import android.util.Log;

import com.GinoAmaury.TVMazeApp.API.API;
import com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor.ISearchActorInteractor;
import com.GinoAmaury.TVMazeApp.Model.Object.People;
import com.GinoAmaury.TVMazeApp.Model.Object.Person;
import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.Model.Object.Show;
import com.GinoAmaury.TVMazeApp.Presenter.SearchActorPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActorInteractor implements ISearchActorInteractor, Callback<List<People>> {

    SearchActorPresenter searchActorPresenter;

    public SearchActorInteractor(SearchActorPresenter searchActorPresenter) {
        this.searchActorPresenter = searchActorPresenter;
    }

    @Override
    public void getActors(String name) {
        Call<List<People>> api = API.getApi().getPersonSearch(name);
        api.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<People>> call, Response<List<People>> response) {
        try{
            if(response.isSuccessful()){
                ArrayList<People> data  = (ArrayList<People>) response.body();
                if(!data.isEmpty()){
                    ArrayList<Person> actors = new ArrayList<>();
                    for (People element: data) {
                        actors.add(element.getPerson());
                        Log.d("ERROR","actor");
                    }
                    if(!actors.isEmpty()){
                        searchActorPresenter.showResult(actors);
                    }else{
                        searchActorPresenter.showResult(null);
                    }
                }else {
                    searchActorPresenter.showResult(null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            searchActorPresenter.showResult(null);
        }
    }

    @Override
    public void onFailure(Call<List<People>> call, Throwable t) {
        searchActorPresenter.showResult(null);
    }
}
