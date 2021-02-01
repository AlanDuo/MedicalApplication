package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ArcMotion;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;

public class NewHealthyArchivesActivity extends AppCompatActivity {
    ImageView newArchivesReturnImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_healthy_archives);

        getSupportActionBar().hide();
        newArchivesReturnImg=findViewById(R.id.iv_new_archives_return);
        newArchivesReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewHealthyArchivesActivity.this,HealthyArchivesActivity.class);
                startActivity(intent);
            }
        });

    }
}
