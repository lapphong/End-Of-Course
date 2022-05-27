package com.dlp.lapphong.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView lsv;
    HistoryAdapter adapter;
    ArrayList<History> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lsv = findViewById(R.id.lsv_history);
        arrayList = new ArrayList<>();
        adapter = new HistoryAdapter(arrayList,HistoryActivity.this);
        lsv.setAdapter(adapter);

        ColorDrawable sage = new ColorDrawable(Color.BLACK);
        lsv.setDivider(sage);
        lsv.setDividerHeight(1);

        readDatabase();
    }

    private void readDatabase() {
        /*=================================== Đọc dữ liệu trong dtb ==============================*/
        Cursor cursor = MainActivity.database.getData("SELECT * FROM History");
        arrayList.clear();
        while(cursor.moveToNext()){
            arrayList.add(new History(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5))
            );
        }
        adapter.notifyDataSetChanged();
    }
}