package com.dlp.lapphong.bt3;

import android.graphics.Bitmap;

public class CountryModel {

    //private Bitmap hinhCountry;
    //private int hinhCountrye;
    private String tenCountry,codeName;
    private Float currencyCountry;

//    public CountryModel(int hinhCountrye, String tenCountry) {
//        this.hinhCountrye = hinhCountrye;
//        this.tenCountry = tenCountry;
//    }


    public CountryModel(String tenCountry, String codeName, Float currencyCountry) {
        this.tenCountry = tenCountry;
        this.codeName = codeName;
        this.currencyCountry = currencyCountry;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Float getCurrencyCountry() {
        return currencyCountry;
    }

    public void setCurrencyCountry(Float currencyCountry) {
        this.currencyCountry = currencyCountry;
    }


//    public CountryModel(Bitmap hinhCountry, String tenCountry) {
//        this.hinhCountry = hinhCountry;
//        this.tenCountry = tenCountry;
//    }

//    public int getHinhCountrye() {
//        return hinhCountrye;
//    }
//
//    public void setHinhCountrye(int hinhCountrye) {
//        this.hinhCountrye = hinhCountrye;
//    }

//    public Bitmap getHinhCountry() {
//        return hinhCountry;
//    }
//
//    public void setHinhCountry(Bitmap hinhCountry) {
//        this.hinhCountry = hinhCountry;
//    }

    public String getTenCountry() {
        return tenCountry;
    }

    public void setTenCountry(String tenCountry) {
        this.tenCountry = tenCountry;
    }
}
