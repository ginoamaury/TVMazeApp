
package com.GinoAmaury.TVMazeApp.Model.Object;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Self implements Serializable
{

    @SerializedName("href")
    @Expose
    private String href;
    private final static long serialVersionUID = -5663262655155937082L;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }



}
