package com.example.inventorymanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViweHolder>{
    private List<OnBoardingItem> onBoardingItems;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }
    @NonNull
    @Override
    public OnboardingViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViweHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViweHolder holder, int position) {
        holder.setOnboardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class OnboardingViweHolder extends RecyclerView.ViewHolder{
        private TextView t1,t2;
        private ImageView imageView;
        public OnboardingViweHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            t1=itemView.findViewById(R.id.tt);
            t2=itemView.findViewById(R.id.tt2);
        }
        void setOnboardingData(OnBoardingItem onBoardingItem){
            t1.setText(onBoardingItem.getTitle());
            t2.setText(onBoardingItem.getDescription());
            imageView.setImageResource(onBoardingItem.getImage());
        }
    }
}
