package com.GinoAmaury.TVMazeApp.Model.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Query {
    @SerializedName("score")
    @Expose
    private double score;
    @SerializedName("show")
    @Expose
    private Search show;


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Search getShow() {
        return show;
    }

    public void setShow(Search show) {
        this.show = show;
    }
}

