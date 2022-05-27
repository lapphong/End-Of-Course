package com.dlp.lapphong.bt2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public void INSERT_HinhAnh(String ten,byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO HinhAnh VALUES(null, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();// phân tích dữ liệu xong clear

        statement.bindString(1,ten);
        statement.bindBlob(2,hinh);

        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
