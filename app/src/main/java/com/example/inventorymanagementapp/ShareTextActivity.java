package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ShareTextActivity extends AppCompatActivity {
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_text);
        Intent intent=getIntent();
        s=intent.getStringExtra("text");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Billing Expenditure : " + s);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        Toast.makeText(getApplicationContext(), "Sharing Bill ...", Toast.LENGTH_SHORT).show();
        finish();
    }
}