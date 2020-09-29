package com.GinoAmaury.TVMazeApp.Model.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast implements Serializable
{

    @SerializedName("self")
    @Expose
    private boolean self;
    @SerializedName("voice")
    @Expose
    private boolean voice;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("_embedded")
    @Expose
    private EmbeddedShow embedded;
    private final static long serialVersionUID = -2127805396235865281L;

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public EmbeddedShow getEmbedded() {
        return embedded;
    }

    public void setEmbedded(EmbeddedShow embedded) {
        this.embedded = embedded;
    }

}
