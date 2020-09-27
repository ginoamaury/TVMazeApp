package com.GinoAmaury.TVMazeApp.Model.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Episode implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("season")
    @Expose
    private int season;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("airdate")
    @Expose
    private String airdate;
    @SerializedName("airtime")
    @Expose
    private String airtime;
    @SerializedName("airstamp")
    @Expose
    private String airstamp;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("_links")
    @Expose
    private Links links;
    private final static long serialVersionUID = -630876264183838890L;


    public Episode() {
    }

    public Episode(int id, String url, String name, int season, int number, String airdate, String airtime, String airstamp, int runtime, Image image, String summary, Links links) {
        super();
        this.id = id;
        this.url = url;
        this.name = name;
        this.season = season;
        this.number = number;
        this.airdate = airdate;
        this.airtime = airtime;
        this.airstamp = airstamp;
        this.runtime = runtime;
        this.image = image;
        this.summary = summary;
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAirdate() {
        return airdate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public String getAirtime() {
        return airtime;
    }

    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    public String getAirstamp() {
        return airstamp;
    }

    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }


}
