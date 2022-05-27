package com.dlp.lapphong.bt2;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country implements Parcelable {
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("languages")
    @Expose
    private String languages;
    @SerializedName("geonameId")
    @Expose
    private Integer geonameId;
    @SerializedName("south")
    @Expose
    private Float south;
    @SerializedName("isoAlpha3")
    @Expose
    private String isoAlpha3;
    @SerializedName("north")
    @Expose
    private Float north;
    @SerializedName("fipsCode")
    @Expose
    private String fipsCode;
    @SerializedName("population")
    @Expose
    private String population;
    @SerializedName("east")
    @Expose
    private Float east;
    @SerializedName("isoNumeric")
    @Expose
    private String isoNumeric;
    @SerializedName("areaInSqKm")
    @Expose
    private String areaInSqKm;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("west")
    @Expose
    private Float west;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("postalCodeFormat")
    @Expose
    private String postalCodeFormat;
    @SerializedName("continentName")
    @Expose
    private String continentName;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    public Country() {
    }

    protected Country(Parcel in) {
        continent = in.readString();
        capital = in.readString();
        languages = in.readString();
        if (in.readByte() == 0) {
            geonameId = null;
        } else {
            geonameId = in.readInt();
        }
        if (in.readByte() == 0) {
            south = null;
        } else {
            south = in.readFloat();
        }
        isoAlpha3 = in.readString();
        if (in.readByte() == 0) {
            north = null;
        } else {
            north = in.readFloat();
        }
        fipsCode = in.readString();
        population = in.readString();
        if (in.readByte() == 0) {
            east = null;
        } else {
            east = in.readFloat();
        }
        isoNumeric = in.readString();
        areaInSqKm = in.readString();
        countryCode = in.readString();
        if (in.readByte() == 0) {
            west = null;
        } else {
            west = in.readFloat();
        }
        countryName = in.readString();
        postalCodeFormat = in.readString();
        continentName = in.readString();
        currencyCode = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Integer getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(Integer geonameId) {
        this.geonameId = geonameId;
    }

    public Float getSouth() {
        return south;
    }

    public void setSouth(Float south) {
        this.south = south;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public Float getNorth() {
        return north;
    }

    public void setNorth(Float north) {
        this.north = north;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Float getEast() {
        return east;
    }

    public void setEast(Float east) {
        this.east = east;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Float getWest() {
        return west;
    }

    public void setWest(Float west) {
        this.west = west;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPostalCodeFormat() {
        return postalCodeFormat;
    }

    public void setPostalCodeFormat(String postalCodeFormat) {
        this.postalCodeFormat = postalCodeFormat;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(continent);
        dest.writeString(capital);
        dest.writeString(languages);
        if (geonameId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(geonameId);
        }
        if (south == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(south);
        }
        dest.writeString(isoAlpha3);
        if (north == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(north);
        }
        dest.writeString(fipsCode);
        dest.writeString(population);
        if (east == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(east);
        }
        dest.writeString(isoNumeric);
        dest.writeString(areaInSqKm);
        dest.writeString(countryCode);
        if (west == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(west);
        }
        dest.writeString(countryName);
        dest.writeString(postalCodeFormat);
        dest.writeString(continentName);
        dest.writeString(currencyCode);
    }
}
