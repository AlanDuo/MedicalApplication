package com.duo.medical.ui.consultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;

public class DoctorIntroActivity extends AppCompatActivity {
    ImageView doctorIntroReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_intro);

        getSupportActionBar().hide();

        doctorIntroReturn=findViewById(R.id.iv_doctor_intro_return);
        doctorIntroReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(DoctorIntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
