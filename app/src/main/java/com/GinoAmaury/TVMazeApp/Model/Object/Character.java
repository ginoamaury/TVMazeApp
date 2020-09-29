package com.GinoAmaury.TVMazeApp.Model.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Character implements Serializable
{
    @SerializedName("href")
    @Expose
    private String href;
    private final static long serialVersionUID = 642093880297020939L;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}