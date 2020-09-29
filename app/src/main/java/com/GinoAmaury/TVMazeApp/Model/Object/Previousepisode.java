
package com.GinoAmaury.TVMazeApp.Model.Object;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Previousepisode implements Serializable
{

    @SerializedName("href")
    @Expose
    private String href;
    private final static long serialVersionUID = -4190273390109436072L;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }



}
