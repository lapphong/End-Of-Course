package com.dlp.lapphong.bt2;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.TooltipCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends BaseAdapter implements Filterable {

    List<Country> countryList;
    List<Country> countryListOld;
    MainActivity context;

    public CountryAdapter(List<Country> countryList, MainActivity context) {
        this.countryList = countryList;
        this.context = context;
        this.countryListOld = countryList;
    }

    @Override
    public int getCount() {
        if(countryList != null){
            return countryList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imageQuocKy,imgageToolTip;
        TextView tenNuoc;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_country, null);
            holder = new ViewHolder();
            holder.imageQuocKy = view.findViewById(R.id.img_item_country);
            holder.tenNuoc = view.findViewById(R.id.textview_item_country);
            holder.imgageToolTip = view.findViewById(R.id.img_tooltip);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tenNuoc.setText(countryList.get(i).getCountryName());
        String url = "http://img.geonames.org/flags/x/" + countryList.get(i).getCountryCode().toLowerCase(Locale.ROOT) + ".gif";
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.img_error)
                .resize(100,70)
                .into(holder.imageQuocKy);

        holder.imgageToolTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.setTooltip(v);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.right_to_left);
        view.startAnimation(animation);

        return view;
    }

    public void runToolTip() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                context.setTooltip(context.findViewById(R.id.img_tooltip));
            }
        },2000);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    countryList = countryListOld;
                } else {
                    List<Country> list = new ArrayList<>();
                    for(Country ds : countryListOld){
                        if(ds.getCountryName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(ds);
                        }
                    }
                    countryList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = countryList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryList = (List<Country>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
