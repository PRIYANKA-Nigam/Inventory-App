package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

public class ShareQRActivity extends AppCompatActivity {
    EditText editText;
    ImageView imageView;
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_q_r);
        editText = findViewById(R.id.editTextTextMultiLine);
        imageView = findViewById(R.id.imageView5);
        Intent intent=getIntent();
        s=intent.getStringExtra("text");
        editText.setText(s);
    }

    public void shareQR(View view) {
        image();
    }

    public void generateQR(View view) {
        String data = editText.getText().toString().trim();
        if (data.isEmpty()) {
            Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show();
        } else {
            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, 350, 350);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(matrix);
                imageView.setImageBitmap(bitmap);
                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                methodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
    private void image() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap =drawable.getBitmap();
        File file =new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
        Intent intent;
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            // intent.putExtra(Intent.EXTRA_TEXT,s);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        startActivity(Intent.createChooser(intent,"share image"));
    }
}