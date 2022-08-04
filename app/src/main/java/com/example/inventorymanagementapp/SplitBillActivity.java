package com.example.inventorymanagementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SplitBillActivity extends AppCompatActivity  {
 String a=""; TextView textView,textView2,textView3;
 EditText editText;
String s1="",s2=""; String mob=""; static int len=0,o=0;
Button b1,b2; double d=0; String mssg=""; int check=0;
Spinner spinner; String message="",price="",price1="",price2="";
ArrayList<String> arrayList =new ArrayList<>();
    ArrayList<String> arrayList2 =new ArrayList<>();
ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);
        spinner=findViewById(R.id.sp);
        AddToSpinner(arrayList);
        textView=findViewById(R.id.textView18);
        textView2=findViewById(R.id.textView14);
        textView3=findViewById(R.id.textView15);
       editText=findViewById(R.id.editTextTextPersonName6);

        a = getIntent().getStringExtra("bill");
        String b = getIntent().getStringExtra("amount");
        check=0; message=a;
        editText.setText(a);
         price=b;
       try {
            String flag = getIntent().getStringExtra("split");  //price,product,date
           message=flag; check=1; editText.setText(flag);
            String[] wow = flag.split(System.lineSeparator());
            String now = wow[0];
            String[] how = now.split(": *");
            String mow = how[1];  //price
           price=mow;
        }catch (NullPointerException e){
           e.printStackTrace();
       }
        textView3.setText("Members per head for "+price+" Rupees: ");
        mssg="Errands List for "+message;
       try {
            o = Integer.parseInt(price);
        }catch (NumberFormatException e){
           e.printStackTrace();
       }

        String num="8932946515, 9129520224, 9599339822";
        SmsManager smsManager=SmsManager.getDefault();
        String numbers[]=num.split(", *");
b1=findViewById(R.id.button4);
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        for (String n :numbers){
//            smsManager.sendTextMessage(n,null,mssg,null,null);
//            Toast.makeText(getApplicationContext(),"Message Send Successfully ...",Toast.LENGTH_SHORT).show();
//
//        }
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.putExtra("sending","8932946515;9129520224;9599339822");
        i.putExtra("sending","My Bills");
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
    }
});
b2=findViewById(R.id.button5);
b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        PackageManager packageManager = getPackageManager();
        try {
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            PackageInfo info =packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_META_DATA);
            intent.setPackage("com.whatsapp");
            intent.putExtra(Intent.EXTRA_TEXT,mssg);
            startActivity(Intent.createChooser(intent,"Share with ..."));
        }catch (PackageManager.NameNotFoundException e){
            Toast.makeText(getApplicationContext(),"Sending Failed  !!!!!!!!\n whatsapp not installed",Toast.LENGTH_SHORT).show();
        }
    }
});
try{
    loadData();
}catch (NullPointerException e){
    e.printStackTrace();
        }
      try  {
            d = o / len;
          textView.setText(String.valueOf(d)); textView.setTextColor(Color.BLACK);
        }catch (NullPointerException|ArithmeticException e){
          e.printStackTrace();
      }
    }

    private void loadData() {
        SharedPreferences sh=getSharedPreferences("member",Context.MODE_PRIVATE);
            Set<String> h = sh.getStringSet("name", null);
            for (String i : h) {
                arrayList.add(i);
                arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                spinner.setAdapter(arrayAdapter);
                try{
                    len = spinner.getAdapter().getCount();
                    textView2.setText(String.valueOf(len)+" Members presently");
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                arrayAdapter.notifyDataSetChanged();
            }
    }

    private void AddToSpinner(ArrayList<String> arrayList) {
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.notifyDataSetChanged();
        if (spinner != null) {
            spinner.setAdapter(arrayAdapter);
            try{
                len = spinner.getAdapter().getCount();
                textView2.setText(String.valueOf(len)+" Members presently");
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            try  {
                d = o / len;
                textView.setText(String.valueOf(d));textView.setTextColor(Color.BLACK);
            }catch (NullPointerException|ArithmeticException e){
                e.printStackTrace();
            }
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String a = (String) adapterView.getSelectedItem();
                    //   mob+=a;
                    Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
//        arrayAdapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
//        spinner.setAdapter(arrayAdapter);

    }
    public void AddMember(View view) {
        Intent intent =new Intent(getApplicationContext(),AddMemberActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                s1 =data.getStringExtra("name");
                s2 =data.getStringExtra("num");
                mob=s2+" ";
                //arrayList2.add(mob);
              //  Toast.makeText(getApplicationContext(),"name: "+s1+" number: "+s2,Toast.LENGTH_SHORT).show();
                String data2="name: "+s1+"\n"+"number: "+s2;
                arrayList.add(data2);
                arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                arrayAdapter.notifyDataSetChanged();
//                AddToSpinner(arrayList);
//                if (spinner != null) {
//                    spinner.setAdapter(arrayAdapter);
//                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                           String a = (String) adapterView.getSelectedItem();
//                        //   mob+=a;
//                            Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> adapterView) {
//
//                        }
//                    });
//                }
                SharedPreferences sh2 = getApplicationContext().getSharedPreferences("member", Context.MODE_PRIVATE);
                HashSet<String> set2 = new HashSet<>(arrayList);
                sh2.edit().putStringSet("name", set2).apply();
                Toast.makeText(getApplicationContext(), "Data Received !!! ..."+mob, Toast.LENGTH_SHORT).show();
                AddToSpinner(arrayList);
            }
            if (resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"Data Fetching Failed",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void DeleteItems(View view) {
        spinner.setAdapter(null);arrayList.removeAll(arrayList);
        arrayAdapter.notifyDataSetChanged();
        SharedPreferences sh=getApplicationContext().getSharedPreferences("member", Context.MODE_PRIVATE);
        HashSet<String> set=new HashSet<>(arrayList);sh.edit().putStringSet("name",set).apply();
        try{
            len = 0;
            textView2.setText(String.valueOf(len)+" Members presently");
//            d = o / len;
            textView.setText("No member to contribute ");textView.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Please add some members to contribute in the whole sum",Toast.LENGTH_LONG).show();
        }catch (NullPointerException|ArithmeticException e){
            e.printStackTrace();
        }
//        try  {
//            d = o / len;
//            textView.setText("No member to contribute ");
//            Toast.makeText(getApplicationContext(),"Please add some members to contribute in the whole sum",Toast.LENGTH_SHORT).show();
//        }catch (NullPointerException|ArithmeticException e){
//            e.printStackTrace();
//        }
    }
}