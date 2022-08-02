package com.example.inventorymanagementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditListActivity extends AppCompatActivity {
    String a="";
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        editText=findViewById(R.id.editTextTextMultiLine);
        setTitle("Edit ListView Item");
        Intent intent =getIntent();
       a =intent.getStringExtra("item");
       editText.setText(a);
    }

//    public void italic(View view) {
////        Spannable spannable =new SpannableStringBuilder(editText.getText().toString());
////        spannable.setSpan(new StyleSpan(Typeface.ITALIC),editText.getSelectionStart(),editText.getSelectionEnd(),0);
////        editText.setText(spannable);
////        a=editText.getText().toString();
//        String source ="<i>"+a+"</i>";
//        a= Html.fromHtml(source).toString();
//        Intent intent1 =new Intent();
//        intent1.putExtra("result",a);
//        setResult(RESULT_OK,intent1);
//        finish();
//    }
//
//    public void noFormatting(View view) {
//
//    }
//
//
//    public void center(View view) {
//        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        Spannable spannable =new SpannableStringBuilder(editText.getText());
//        editText.setText(spannable);
//        a=editText.getText().toString();
//    }
//
//    public void right(View view) {
//        editText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
//        Spannable spannable =new SpannableStringBuilder(editText.getText());
//        editText.setText(spannable);
//        a=editText.getText().toString();
//    }
//
//    public void left(View view) {
//        editText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
//        Spannable spannable =new SpannableStringBuilder(editText.getText());
//        editText.setText(spannable);
//        a=editText.getText().toString();
//    }
//
//    public void underline(View view) {
////        Spannable spannable =new SpannableStringBuilder(editText.getText());
////        spannable.setSpan(new UnderlineSpan(),editText.getSelectionStart(),editText.getSelectionEnd(),0);
////        editText.setText(spannable);
////        a=editText.getText().toString();
//        String source ="<u>"+a+"</u>";
//        a= Html.fromHtml(source).toString();
//        Intent intent1 =new Intent();
//        intent1.putExtra("result",a);
//        setResult(RESULT_OK,intent1);
//        finish();
//    }

    public void sendBack(View view) {
        a=editText.getText().toString();
        Intent intent1 =new Intent();
        intent1.putExtra("result",a);
        setResult(RESULT_OK,intent1);
        finish();
    }
}