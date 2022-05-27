package com.dlp.lapphong.bt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar skBar;
    TextView tv1,tv2,tv3,tv4,tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        int color1 = ((ColorDrawable) tv1.getBackground()).getColor();
        int color2 = ((ColorDrawable) tv2.getBackground()).getColor();
        int color3 = ((ColorDrawable) tv3.getBackground()).getColor();
        int color5 = ((ColorDrawable) tv5.getBackground()).getColor();

        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tv1.setBackgroundColor(Color.rgb(23 - progress, 200 + progress, 99 + progress));
                tv2.setBackgroundColor(Color.rgb(255, 133 + progress, 144 - progress));
                tv3.setBackgroundColor((Color.rgb(88 + progress, 200 - progress, 177 + progress)));
                tv5.setBackgroundColor(Color.rgb(230, 56 + progress, 233 - progress));

                //tv1.setBackgroundColor(transitionColor(0xf2697FFB,0xf2F0E68C,progress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private static int transitionColor(int startColor, int finalColor, float percentage) {
        int transitionRed = (int) Color.red(startColor) + Math.round((Color.red(finalColor)-Color.red(startColor)) * (float) percentage);
        int transitionGreen = (int) Color.green(startColor) + Math.round((Color.green(finalColor)-Color.green(startColor)) * (float) percentage);
        int transitionBlue = (int) Color.blue(startColor) + Math.round((Color.blue(finalColor)-Color.blue(startColor)) * (float) percentage);
        return Color.argb(Color.alpha(startColor), transitionRed, transitionGreen, transitionBlue);
    }

    private void anhXa() {
        skBar = (SeekBar) findViewById(R.id.seekBar);
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        tv5 = (TextView) findViewById(R.id.tv_5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuMoreInformation:
                //Toast.makeText(MainActivity.this, "Đang chọn click menu More Information", Toast.LENGTH_SHORT).show();
                openDilog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDilog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView tv_Visit_MOMA = dialog.findViewById(R.id.tv_visit_MOMA);
        TextView tv_Not_Now = dialog.findViewById(R.id.tv_Not_Now);

        tv_Not_Now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_Visit_MOMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.moma.org";
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        dialog.show();
    }
}