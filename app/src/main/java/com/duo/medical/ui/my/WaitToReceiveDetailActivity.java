package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;

public class WaitToReceiveDetailActivity extends AppCompatActivity {
    ImageView receiveDetailReturnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_receive_detail);

        getSupportActionBar().hide();

        receiveDetailReturnImg=findViewById(R.id.iv_wait_to_receive_detail_return);
        receiveDetailReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToReceiveDetailActivity.this,WaitToReceiveActivity.class);

                startActivity(intent);
            }
        });
    }
}
