package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;

public class ShareActivity extends AppCompatActivity {
    String s="";
    private ImageView i1,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent intent=getIntent();
        s=intent.getStringExtra("share");
        i1=findViewById(R.id.i1);i1.setTooltipText("Share Bill as Normal Text");
        i2=findViewById(R.id.i3); i2.setTooltipText("Share Bill in encoded format as QR ");
        TextDrawable roundRect = TextDrawable.builder().beginConfig()
                .width(170)  // width in px
                .height(170) // height in px
                .endConfig()
                .buildRound("T", getResources().getColor(R.color.purple_200)); // radius in px
        i1.setImageDrawable(roundRect);
        TextDrawable roundRect2 = TextDrawable.builder().beginConfig()
                .width(170)  // width in px
                .height(170) // height in px
                .endConfig()
                .buildRound("Q", getResources().getColor(R.color.purple_200)); // radius in px
        i2.setImageDrawable(roundRect2);
    }

    public void shareText(View view) {
        Intent intent=new Intent(getApplicationContext(),ShareTextActivity.class);
        intent.putExtra("text",s);
        startActivity(intent);
    }

    public void shareQR(View view) {
        Intent intent=new Intent(getApplicationContext(),ShareQRActivity.class);
        intent.putExtra("text",s);
        startActivity(intent);
    }
}