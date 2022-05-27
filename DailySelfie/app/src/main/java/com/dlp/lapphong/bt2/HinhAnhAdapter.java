package com.dlp.lapphong.bt2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HinhAnhAdapter extends BaseAdapter {

    List<HinhAnh> hinhAnhList;
    MainActivity context;

    public HinhAnhAdapter(List<HinhAnh> hinhAnhList, MainActivity context) {
        this.hinhAnhList = hinhAnhList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hinhAnhList.size();
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
        ImageView image,imgDel;
        TextView ten;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_hinh_anh,null);
            holder = new ViewHolder();
            holder.image=(ImageView) view.findViewById(R.id.imageView);
            holder.ten = (TextView) view.findViewById(R.id.text_name_hinh);
            holder.imgDel = (ImageView) view.findViewById(R.id.img_delete);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        HinhAnh hinhanh = hinhAnhList.get(i);
        holder.ten.setText(hinhanh.getTen());

        byte[] hinhAnh = hinhanh.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.image.setImageBitmap(bitmap);

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoa(hinhanh.getTen(),hinhanh.getId());
            }
        });

        return view;
    }
}
