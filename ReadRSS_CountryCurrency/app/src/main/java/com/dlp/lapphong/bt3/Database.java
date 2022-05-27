package com.dlp.lapphong.bt3;

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

    public void INSERT(String ten,byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO HinhAnh VALUES(null, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();// phân tích dữ liệu xong clear

        statement.bindString(1,ten);
        statement.bindBlob(2,hinh);

        statement.executeInsert();
    }

    public void INSERT2(String codeFROM,String codeTO,String input,String result,String date){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO History VALUES(null, ?, ?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();// phân tích dữ liệu xong clear

        statement.bindString(1,codeFROM);
        statement.bindString(2,codeTO);
        statement.bindString(3,input);
        statement.bindString(4,result);
        statement.bindString(5,date);

        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
