package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;

public class WaitToPayDetailActivity extends AppCompatActivity {
    ImageView waitToPayDetailReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_pay_detail);

        getSupportActionBar().hide();

        waitToPayDetailReturn=findViewById(R.id.iv_wait_to_pay_detail_return);
        waitToPayDetailReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToPayDetailActivity.this,WaitToPayActivity.class);
                startActivity(intent);
            }
        });
    }
}
