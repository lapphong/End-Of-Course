package com.dlp.lapphong.bt3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends BaseAdapter {

    List<CountryModel> countryModelList;
    Context context;

    public CountryAdapter(List<CountryModel> countryModelList, Context context) {
        this.countryModelList = countryModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return countryModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView image;
        TextView ten;
    }
    ViewHolder holder;
    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_country,null);
            holder = new ViewHolder();
            holder.image=(ImageView) view.findViewById(R.id.img_Country_name);
            holder.ten = (TextView) view.findViewById(R.id.textView_Name_Currency_Country);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        CountryModel country = countryModelList.get(i);
        holder.ten.setText(country.getCodeName());
        //holder.image.setImageBitmap(country.getHinhCountry());

        String url = "https://www.fxexchangerate.com/static/flags/"+country.getCodeName().toLowerCase()+".webp";
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.img_error).into(holder.image);

        return view;
    }
}
