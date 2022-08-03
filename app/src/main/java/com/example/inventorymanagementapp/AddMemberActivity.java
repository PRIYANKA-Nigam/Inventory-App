package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMemberActivity extends AppCompatActivity {
EditText e1,e2;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        button=findViewById(R.id.button6);
        e1=findViewById(R.id.editTextTextPersonName5);
        e2=findViewById(R.id.editTextNumber5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                e1.setText("");e2.setText("");
                Intent intent1 =new Intent();
                intent1.putExtra("name",s1);
                intent1.putExtra("num",s2);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }
}