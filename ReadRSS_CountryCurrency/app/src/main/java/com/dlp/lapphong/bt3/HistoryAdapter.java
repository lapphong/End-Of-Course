package com.dlp.lapphong.bt3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends BaseAdapter {

    List<History> historyList;
    Context context;

    public HistoryAdapter(List<History> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return historyList.size();
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
        ImageView imagecodeFROM,imgcodeTO;
        TextView input,result,date;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_history,null);
            holder = new ViewHolder();
            holder.imagecodeFROM=view.findViewById(R.id.img_codefrom);
            holder.imgcodeTO=view.findViewById(R.id.img_codeto);
            holder.input = view.findViewById(R.id.textview_input);
            holder.result = view.findViewById(R.id.textview_result);
            holder.date = view.findViewById(R.id.textview_date);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        History history = historyList.get(i);
        String urlFrom = "https://www.fxexchangerate.com/static/flags/"+history.getCodeFROM().toLowerCase()+".webp";
        Picasso.get().load(urlFrom)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.img_error)
                .into(holder.imagecodeFROM);

        String urlTo = "https://www.fxexchangerate.com/static/flags/"+history.getCodeTO().toLowerCase()+".webp";
        Picasso.get().load(urlTo)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.img_error)
                .into(holder.imgcodeTO);
        holder.input.setText("Input: "+history.getInput());
        holder.result.setText("Result: "+history.getResult());
        holder.date.setText(history.getDate());

        return view;
    }
}
