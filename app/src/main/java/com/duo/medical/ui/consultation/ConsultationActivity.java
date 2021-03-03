package com.duo.medical.ui.consultation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.duo.medical.R;

public class ConsultationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        getSupportActionBar().hide();


    }
}
