package com.dlp.lapphong.bt2.Retrofit2;
import com.dlp.lapphong.bt2.CountryList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataClient {
    @GET("countryInfoJSON?&username=lapphong305")
    Call<CountryList> getDATA();
}
