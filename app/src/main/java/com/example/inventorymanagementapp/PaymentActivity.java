package com.example.inventorymanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    AppCompatButton a;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        t=findViewById(R.id.textView10);
        a=findViewById(R.id.app);
        Checkout.preload(getApplicationContext());
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentNow("500");
            }
        });
    }
    private void paymentNow(String s) {
        final Activity activity=this;
        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_jBZBdLNNmrnm5T");
        checkout.setImage(R.drawable.baby);
        double finalAmount =Float.parseFloat(s)*100;
        try {
            JSONObject object =new JSONObject();
            object.put("name","Priya");
            object.put("description","Reference No. #123456");
            object.put("image","https://picsum.photos/200");
            object.put("theme.color","#3399cc");
            object.put("currency","INR");
            object.put("amount",finalAmount++);
            object.put("prefill.email","nigampriyanka042@okhdfcbank");
            object.put("prefill.contact","#8932846515");
            checkout.open(activity,object);

        }catch (Exception e){
            Log.e("TAG","Error in starting RazorPay Checkout",e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(),"Payment Success",Toast.LENGTH_SHORT).show();
        t.setText(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),"Payment Failed !!!!!!!",Toast.LENGTH_SHORT).show();
        t.setText(s);
    }
}