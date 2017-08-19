
package com.example.admin.flickrapp.model;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;

public class Media {

    @SerializedName("m")
    @Expose
    private String m;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

}
