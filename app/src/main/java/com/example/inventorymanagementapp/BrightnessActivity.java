package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Set;

public class BrightnessActivity extends AppCompatActivity {
EditText e; Button button;
SeekBar seekBar; TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        e=findViewById(R.id.editTextNumber4);
        button=findViewById(R.id.button3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)){
                Intent intent=new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:"+this.getPackageName()));
                startActivity(intent);
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver=getApplicationContext().getContentResolver();
                Settings.System.putInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS,Integer.parseInt(e.getText().toString()));

            }
        });
        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView10);
        int bright =Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,0);
        textView.setText(bright+"/255");
        seekBar.setProgress(bright);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Context context =getApplicationContext();
                boolean canWrite =Settings.System.canWrite(context);
                if (canWrite){
                    int sBright =progress*255/255;
                    textView.setText(sBright+"/255");
                    Settings.System.putInt(context.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                    Settings.System.putInt(context.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,sBright);

                }else {
                    Intent intent =new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}