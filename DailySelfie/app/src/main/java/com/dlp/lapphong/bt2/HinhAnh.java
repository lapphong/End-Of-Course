package com.dlp.lapphong.bt2;

import android.graphics.Bitmap;

public class HinhAnh {

    private int Id;
    private String ten;
    private byte[] hinh;

    public HinhAnh(int id, String ten, byte[] hinh) {
        this.Id = id;
        this.ten = ten;
        this.hinh = hinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
