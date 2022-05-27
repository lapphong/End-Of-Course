package com.dlp.lapphong.bt2.Retrofit2;

public class APIUtils {
    public static final String Base_url = "http://api.geonames.org/";

    //gửi và nhận dữ liệu về chỉ chứa trong Interface
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_url).create(DataClient.class);
    }
}
