package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private OnBoardingAdapter adapter;
    private LinearLayout layout;
    private MaterialButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        setUpOnboardingItem();
        ViewPager2 viewPager2=findViewById(R.id.vv);
        viewPager2.setAdapter(adapter);
        layout=findViewById(R.id.lin);
        button=findViewById(R.id.bt);
        setUpOnboardingIndicator();
        setCurrentOnboardingIndicator(0);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager2.getCurrentItem()+1<adapter.getItemCount()){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });
    }
    private void setUpOnboardingItem(){
        List<OnBoardingItem> onBoardingItems=new ArrayList<>();
        OnBoardingItem onBoardingItem =new OnBoardingItem();
        onBoardingItem.setTitle("This is the Home Screen");
        onBoardingItem.setDescription("Buy the Daily Groceries,stationaries,and other items from the store and create your own database of all the brought items at ease");
        onBoardingItem.setImage(R.drawable.groceries);
        OnBoardingItem boardingItem=new OnBoardingItem();
        boardingItem.setTitle("This is the next Screen");
        boardingItem.setDescription("List all the items and calculate your daily budget ");
        boardingItem.setImage(R.drawable.calculate);
        OnBoardingItem onBoardingItem2 =new OnBoardingItem();
        onBoardingItem2.setTitle("This is the main Screen");
        onBoardingItem2.setDescription("Prepare your own inventory with just one click from the app");
        onBoardingItem2.setImage(R.drawable.ruppee);
        onBoardingItems.add(onBoardingItem);
        onBoardingItems.add(boardingItem);
        onBoardingItems.add(onBoardingItem2);
        adapter =new OnBoardingAdapter(onBoardingItems);

    }
    private void setUpOnboardingIndicator(){
        ImageView[] imageViews=new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,0,0,0);
        for (int i=0;i<imageViews.length;i++){
            imageViews[i] =new ImageView(getApplicationContext());
            imageViews[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.calculate));
            layout.addView(imageViews[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int count=layout.getChildCount();
        for (int i=0;i<count;i++){
            ImageView imageView=(ImageView) layout.getChildAt(i);
            if (i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator2));
            }
        }
        if (index==adapter.getItemCount()-1){
            button.setText("Start");
        }else {
            button.setText("Next");
        }
    }
    @Override
    public void onUserLeaveHint () {
        PictureInPictureParams pictureInPictureParams= new PictureInPictureParams.Builder().build();
        enterPictureInPictureMode(pictureInPictureParams);

    }
}