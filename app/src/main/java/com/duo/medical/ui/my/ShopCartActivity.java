package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.duo.medical.R;

public class ShopCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);

        getSupportActionBar().hide();

    }
}
