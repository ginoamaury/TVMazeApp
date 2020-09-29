package com.GinoAmaury.TVMazeApp.Interfaces.Search.Actor;

import com.GinoAmaury.TVMazeApp.Model.Object.Person;

import java.util.ArrayList;

public interface ISearchActorPresenter {
    void getActors (String name);
    void showResult (ArrayList<Person> actors);
}
