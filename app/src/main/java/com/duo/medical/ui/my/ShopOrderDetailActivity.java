package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;

public class ShopOrderDetailActivity extends AppCompatActivity {
    ImageView orderDetailReturnImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_order_detail);

        getSupportActionBar().hide();

        orderDetailReturnImg=findViewById(R.id.iv_shop_order_detail_return);
        orderDetailReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopOrderDetailActivity.this,OrderActivity.class);

                startActivity(intent);
            }
        });
    }
}
