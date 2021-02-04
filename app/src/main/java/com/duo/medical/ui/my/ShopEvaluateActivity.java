package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;

public class ShopEvaluateActivity extends AppCompatActivity {
    ImageView evaluateReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_evaluate);

        getSupportActionBar().hide();

        evaluateReturn=findViewById(R.id.iv_shop_order_evaluate_return);
        evaluateReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopEvaluateActivity.this,OrderActivity.class);

                startActivity(intent);
            }
        });
    }
}
