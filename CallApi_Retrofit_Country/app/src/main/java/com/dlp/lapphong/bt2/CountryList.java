package com.dlp.lapphong.bt2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CountryList {

    @SerializedName("geonames")
    @Expose
    private ArrayList<Country> geonames = null;

    public ArrayList<Country> getGeonames() {
        return geonames;
    }

    public void setGeonames(ArrayList<Country> geonames) {
        this.geonames = geonames;
    }
}
