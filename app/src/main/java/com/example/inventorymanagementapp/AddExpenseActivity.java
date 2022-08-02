package com.example.inventorymanagementapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddExpenseActivity extends AppCompatActivity {
ArrayList<String> list=new ArrayList<>(); ArrayList<String> userSelection =new ArrayList<>();
ArrayAdapter adapter;  String r="";
    ListView listView; String c="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false; }
        });
//        arrayList=new ArrayList<>();
        Calendar calendar =Calendar.getInstance();
        String curDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
       try {
         //   list = new ArrayList<>();
            Intent intent=getIntent();
            String p=intent.getStringExtra("pro");
            String s = intent.getStringExtra("see");
            if (!s.equals("")) {
                c ="Price: "+ s + "       (Date: " + curDate + ")"+"\n "+p;
//                arrayList.add(new Modal(s,p,curDate));
//                adapter=new MyAdapter(getApplicationContext(),arrayList);
//                adapter.notifyDataSetChanged();
                list.add(c);
                adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
                adapter.notifyDataSetChanged();
            }
        }catch (NullPointerException|NumberFormatException e){
           e.printStackTrace();
       }

        listView=findViewById(R.id.ll);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(modeListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              try {
                  for (int it=0;it<list.size();it++){
                      if (i==it){
                          listView.getChildAt(it).setBackgroundColor(Color.WHITE);
                      }else {
                          listView.getChildAt(it).setBackgroundColor(getColor(R.color.red));

                      }
                  }
              }catch (Exception e){
                  e.printStackTrace();
              }
              String s=  list.get(i);
              Intent intent=new Intent(getApplicationContext(),EditListActivity.class);
              intent.putExtra("item",s);
              startActivityForResult(intent,1);
                list.set(i,r);
                adapter.notifyDataSetChanged();
            }
        });
        LayoutInflater inflater =getLayoutInflater();  //for adding header to listView
        ViewGroup header =(ViewGroup) inflater.inflate(R.layout.header,listView,false);
        listView.addHeaderView(header,null,false);

        try{
            loadData();

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    @Override
    public void onUserLeaveHint () {
        PictureInPictureParams pictureInPictureParams= new PictureInPictureParams.Builder().build();
        enterPictureInPictureMode(pictureInPictureParams);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
               r =data.getStringExtra("result");
                Toast.makeText(getApplicationContext(),"Modified",Toast.LENGTH_SHORT).show();
            }
            if (resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"Modification Failed",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void removeItem(List<String> arrayList){
        for (String i : arrayList){
            list.remove(i);
        }
    }
    private void saveItem(ArrayList<String> userSelection) {
//        SharedPreferences sh=getSharedPreferences("expense", Context.MODE_PRIVATE);
//        HashSet<String> set=new HashSet<>(userSelection);
//        sh.edit().putStringSet("event",set).apply();
//        Toast.makeText(this,"Items Saved",Toast.LENGTH_SHORT).show();
    }

    private void shareItem(ArrayList<String> arrayList) {
        String select="";
        for (String s : arrayList)
            select+=s+"\n";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Billing Expenditure : " + select);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        Toast.makeText(getApplicationContext(), "Sharing Bill ...", Toast.LENGTH_SHORT).show();
        finish();

    }
    AbsListView.MultiChoiceModeListener modeListener =new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
            if (userSelection.contains(list.get(i))){
                userSelection.remove(list.get(i));
            }else {
                userSelection.add(list.get(i));
            }
            actionMode.setTitle(userSelection.size() +" items selected...");
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater menuInflater =actionMode.getMenuInflater();
            menuInflater.inflate(R.menu.delete_save_share,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.delete:
                    removeItem(userSelection);
                    adapter.notifyDataSetChanged();
                    SharedPreferences sh = getApplicationContext().getSharedPreferences("expense", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet<>(list);
                    sh.edit().putStringSet("event", set).apply();
                    Toast.makeText(getApplicationContext(), "Deleted !!! ...", Toast.LENGTH_SHORT).show();
                    actionMode.finish();
                    return true;
                case R.id.share:
                    shareItem(userSelection);
                    return true;
                case R.id.save:
                    saveItem(userSelection);
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_dark, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void loadData() {
        SharedPreferences sh = getSharedPreferences("expense", MODE_PRIVATE);
        Set<String> set = sh.getStringSet("event", new HashSet<String>());
        for (String i : set) {
           list.add(i);
            adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save :
                SharedPreferences sh=getSharedPreferences("expense", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(list);
                sh.edit().putStringSet("event",set).apply();
                Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dark:
                startActivity(new Intent(getApplicationContext(),DarkModeActivity.class));finish();
        }
        return false;
    }

    public void ScreenBrightness(View view) {
        startActivity(new Intent(getApplicationContext(),BrightnessActivity.class));
    }
}