
package com.GinoAmaury.TVMazeApp.Model.Object;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Externals implements Serializable
{

    @SerializedName("tvrage")
    @Expose
    private int tvrage;
    @SerializedName("thetvdb")
    @Expose
    private int thetvdb;
    @SerializedName("imdb")
    @Expose
    private String imdb;
    private final static long serialVersionUID = 3964908737649382774L;

    public int getTvrage() {
        return tvrage;
    }

    public void setTvrage(int tvrage) {
        this.tvrage = tvrage;
    }

    public int getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(int thetvdb) {
        this.thetvdb = thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }



}
