package com.dlp.lapphong.bt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ChiTietHinhAnhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hinh_anh);

        ImageView img = (ImageView) findViewById(R.id.chitiet_hinhanh);

        Intent intent = getIntent();
        byte[] nhanHinh = intent.getByteArrayExtra("hinhanh");
        Bitmap bitmap = BitmapFactory.decodeByteArray(nhanHinh,0,nhanHinh.length);
        img.setImageBitmap(bitmap);
    }
}