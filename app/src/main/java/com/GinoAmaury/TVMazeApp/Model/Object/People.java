package com.GinoAmaury.TVMazeApp.Model.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class People implements Serializable {
    @SerializedName("score")
    @Expose
    private double score;
    @SerializedName("person")
    @Expose
    private Person person;
    private final static long serialVersionUID = 3510346886177860859L;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

