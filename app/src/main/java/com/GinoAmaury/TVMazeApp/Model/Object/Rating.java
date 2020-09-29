
package com.GinoAmaury.TVMazeApp.Model.Object;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating implements Serializable
{

    @SerializedName("average")
    @Expose
    private double average;
    private final static long serialVersionUID = 2655584558093540805L;

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }



}
