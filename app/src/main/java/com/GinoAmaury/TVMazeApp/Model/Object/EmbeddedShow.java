package com.GinoAmaury.TVMazeApp.Model.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmbeddedShow implements Serializable
{
    @SerializedName("show")
    @Expose
    private Show show;
    private final static long serialVersionUID = -2227774824842937471L;

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

}
