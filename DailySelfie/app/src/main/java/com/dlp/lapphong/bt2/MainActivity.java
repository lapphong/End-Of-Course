package com.dlp.lapphong.bt2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE = 100;
    public static Database database;
    ListView lvHinhAnh;
    ArrayList<HinhAnh> arrayHinhAnh;
    HinhAnhAdapter adapter;

//    Button btnCancel,btnSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this,"QuanLy.sqlite",null,1);
        database.queryData("CREATE TABLE IF NOT EXISTS HinhAnh(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), HinhAnh BLOB)");

        lvHinhAnh = (ListView) findViewById(R.id.lvHinhAnh);
        arrayHinhAnh = new ArrayList<>();
        adapter = new HinhAnhAdapter(arrayHinhAnh,this);
        lvHinhAnh.setAdapter(adapter);

        ColorDrawable sage = new ColorDrawable(Color.BLACK);
        lvHinhAnh.setDivider(sage);
        lvHinhAnh.setDividerHeight(1);

        readDatabase();

        lvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "đang click item trên listview", Toast.LENGTH_SHORT).show();

                byte[] item = arrayHinhAnh.get(position).getHinh();
                Intent intent = new Intent(MainActivity.this,ChiTietHinhAnhActivity.class);
                intent.putExtra("hinhanh",item);
                startActivity(intent);
            }
        });

//        btnCancel = (Button) findViewById(R.id.btnCancel);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancelAlarm();
//            }
//        });
        cancelAlarm();
        createNotificationChannel();
//        btnSetAlarm = (Button) findViewById(R.id.btnSet);
//        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupAlarm();
//            }
//        });
    }

    private void readDatabase() {
        /*=================================== Đọc dữ liệu trong dtb ==============================*/
        Cursor cursor = database.getData("SELECT * FROM HinhAnh");
        arrayHinhAnh.clear();
        while(cursor.moveToNext()){
            arrayHinhAnh.add(new HinhAnh(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getBlob(2))
            );
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.camera_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_camera){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    private void openIntentCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mActivityResultLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            openIntentCamera();
        }else{
            Toast.makeText(MainActivity.this, "Không cho phép mở camera", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Dialog dialog = new Dialog(MainActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_dialog_custom);
                        dialog.setCanceledOnTouchOutside(false);

                        Window window = dialog.getWindow();
                        if (window== null) { return; }
                        window.setLayout (WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams. WRAP_CONTENT);
                        window.setBackgroundDrawable (new ColorDrawable (Color. TRANSPARENT));
                        WindowManager.LayoutParams windowAttributes = window.getAttributes();
                        windowAttributes.gravity = Gravity.CENTER;
                        window.setAttributes (windowAttributes);

                        Button btnDongY = (Button) dialog.findViewById(R.id.btnDongY);
                        Button btnBoQuaChupLai = (Button) dialog.findViewById(R.id.btnBoQua);

                        btnDongY.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = result.getData();
                                Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                                byte[] hinhAnh = byteArray.toByteArray();
                                dialog.dismiss();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    MainActivity.database.INSERT_HinhAnh(
                                            new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()),
                                            hinhAnh
                                    );
                                }
                                readDatabase();
                            }
                        });
                        btnBoQuaChupLai.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                openIntentCamera();
                            }
                        });
                        dialog.show();
                    }
                }
            });

    private void createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "LemubitReminderChannel";
            String description = "Channel for Lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = null;
            channel = new NotificationChannel("channelID", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private PendingIntent pendingIntent;
    private static final long INITIAL_ALARM_DELAY = 2 * 60 * 1000L;
    private void setupAlarm() {
        Intent intent = new Intent(MainActivity.this,AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        long tenSecondsInMillis = 1000 * 3;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeAtButtonClick+ tenSecondsInMillis,
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void cancelAlarm() {
        if (null == pendingIntent)
            return;
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
    }

    public void DialogXoa(String tencv,int id){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Bạn có chắc muốn xóa "+tencv+" này không ?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.queryData("DELETE FROM HinhAnh WHERE Id = '"+ id +"' ");
                Toast.makeText(MainActivity.this, "Đã xóa:"+tencv, Toast.LENGTH_SHORT).show();
                readDatabase();
            }
        });

        dialog.setNegativeButton("Hông pé ơi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private long backPressedTime;
    private Toast mToast;
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
            super.onBackPressed();
            setupAlarm();
            return;
        }else{
            mToast = Toast.makeText(MainActivity.this,"Nhấp lần nữa để thoát app",Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        Log.d("AAA","Đã dừng");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("AAA","Đã Destroy");
        setupAlarm();
        super.onDestroy();
    }
}