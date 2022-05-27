package com.dlp.lapphong.bt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.MeasureUnit;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class CountryInforActivity extends AppCompatActivity {

    private Country countryDetail;
    private ImageView img_Flag,img_Map;
    private TextView tenNuoc,danSo,dienTich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_infor);

        Intent intent = getIntent();
        countryDetail = new Country();
        Bundle bundle = intent.getBundleExtra("dulieu");
        if(bundle != null){
            countryDetail = (Country) bundle.getParcelable("countryDetail");
        }

//        Log.d("AAA","Tên nước:"+countryDetail.getCountryName());
//        Log.d("AAA","Dân số:"+countryDetail.getPopulation());
//        Log.d("AAA","Diện tích:"+countryDetail.getAreaInSqKm());
//        Log.d("AAA","Country code:"+countryDetail.getCountryCode());

        anhXa();
        tenNuoc.setText("Country Name:"+countryDetail.getCountryName());
        danSo.setText("Population:"+countryDetail.getPopulation());
        dienTich.setText("AreaInSqKm:"+countryDetail.getAreaInSqKm()+" km2");

        String urlFlag = "http://img.geonames.org/flags/x/" + countryDetail.getCountryCode().toLowerCase(Locale.ROOT) + ".gif";
        String urlMap = "http://img.geonames.org/img/country/250/"+countryDetail.getCountryCode()+".png";

        Picasso.get()
                .load(urlFlag)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.img_error)
                .into(img_Flag);

        Picasso.get()
                .load(urlMap)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.img_error)
                .into(img_Map);
    }

    private void anhXa() {
        img_Flag = findViewById(R.id.img_country_flag);
        img_Map = findViewById(R.id.img_Country_map);
        tenNuoc = findViewById(R.id.textView_CountryName);
        danSo = findViewById(R.id.textView_Population);
        dienTich = findViewById(R.id.textView_areaInSqKm);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        img_Flag.startAnimation(animation);
        img_Map.startAnimation(animation);
        tenNuoc.startAnimation(animation);
        danSo.startAnimation(animation);
        dienTich.startAnimation(animation);
    }
}