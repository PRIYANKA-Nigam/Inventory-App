package com.example.inventorymanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
private ArrayList<String> d1=new ArrayList<>();
    private ArrayList<String> d2=new ArrayList<>();
    private ArrayList<String> d3=new ArrayList<>();
    private ArrayList<String> d4=new ArrayList<>();
    TableLayout tableLayout;
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b,b2; String expense="",product="",fixed="";
    ArrayList<String> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),AddExpenseActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.member:
                        startActivity(new Intent(getApplicationContext(),AddNewMemberActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false; }
        });
        e1=findViewById(R.id.editTextTextPersonName);
        e2=findViewById(R.id.editTextTextPersonName2);
        e3=findViewById(R.id.editTextTextPersonName3);
        e4=findViewById(R.id.editTextTextPersonName4);
        e5=findViewById(R.id.editTextNumber);
        e6=findViewById(R.id.editTextNumber2);
        e7=findViewById(R.id.editTextNumber3);
        b=findViewById(R.id.button);  b2=findViewById(R.id.button2);
        tableLayout=findViewById(R.id.tl);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(); expense=e4.getText().toString();
                SharedPreferences sh =getSharedPreferences("chart", Context.MODE_PRIVATE);
                sh.edit().putString("price", expense).putString("product", product).apply();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh =getSharedPreferences("chart", Context.MODE_PRIVATE);
                String s1=  sh.getString("price",null);
                String s2=  sh.getString("product",null);
                Calendar calendar =Calendar.getInstance();
                String curDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
          fixed="Price :"+s1+"\n"+"-------------------------------"+"\n"+s2+
        "\n"+"------------------"+"Date: "+curDate;
          list.add(fixed);
                Intent intent=new Intent(getApplicationContext(),AddExpenseActivity.class);
                intent.putStringArrayListExtra("quote", list).putExtra("s1",s1);
                Toast.makeText(getApplicationContext(),"Data Passed to the List ...",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        e5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
              try  {
                    int subTotal = Integer.parseInt(e4.getText().toString());
                    int pay = Integer.parseInt(e5.getText().toString());
                    int bal = pay - subTotal;
                    e6.setText(String.valueOf(bal));
                }catch(NumberFormatException e){
                  e.printStackTrace();
              }
            }
        });
    }
    public void add() {
        int t,q;
        try {
        String p = e1.getText().toString().trim();
        int pr = Integer.parseInt(e2.getText().toString().trim());
        if(!(e3.getText().toString().trim()).equals(""))
            q = Integer.parseInt(e3.getText().toString().trim());
        else
            q=1;


            if (!p.equals("") && !(e2.getText().toString().trim()).equals("") ) {
                t = q * pr;
                d1.add(p); product+="- "+p+"\n";
                d2.add(String.valueOf(pr));
                d3.add(String.valueOf(q));
                d4.add(String.valueOf(t));
                TableRow tableRow = new TableRow(this);
                TextView t1 = new TextView(this);
                TextView t2 = new TextView(this);
                TextView t3 = new TextView(this);
                TextView t4 = new TextView(this);
                String total;
                int sum = 0;
                for (int i = 0; i < d1.size(); i++) {
                    String pro = d1.get(i);
                    String pri = d2.get(i);
                    String qty = d3.get(i);
                    total = d4.get(i);
                    t1.setText(pro);
                    t2.setText(pri);
                    t3.setText(qty);
                    t4.setText(total);
                    sum += Integer.parseInt(total);
                }
                tableRow.addView(t1);
                tableRow.addView(t2);
                tableRow.addView(t3);
                tableRow.addView(t4);
                tableLayout.addView(tableRow);
                e4.setText(String.valueOf(sum));    e7.setText(String.valueOf(sum));
                e1.setText(" ");
                e2.setText(" ");
                e3.setText(" ");
                e1.requestFocus();
            } else {
                Toast.makeText(getApplicationContext(), "Product/price/Quantity are the required field!!!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.night_mode, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dark:
                   startActivity(new Intent(getApplicationContext(),DarkModeActivity.class));break;
            case R.id.money:
                SharedPreferences sh =getSharedPreferences("chart", Context.MODE_PRIVATE);
                String s1=  sh.getString("price",null);
                String s2=  sh.getString("product",null);
                Calendar calendar =Calendar.getInstance();
                String curDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                fixed="Price :"+s1+"\n"+"-------------------------------"+"\n"+s2+
                        "\n"+"------------------"+"Date: "+curDate;
                Intent intent =new Intent(getApplicationContext(),SplitBillActivity.class);
                intent.putExtra("bill",fixed);
                intent.putExtra("amount",s1);
                startActivity(intent);
                break;

        }
        return false;
    }

    public void ScreenBrightness(View view) {
        startActivity(new Intent(getApplicationContext(),BrightnessActivity.class));
    }
    @Override
    public void onUserLeaveHint () {
        PictureInPictureParams pictureInPictureParams= new PictureInPictureParams.Builder().build();
        enterPictureInPictureMode(pictureInPictureParams);

    }
}