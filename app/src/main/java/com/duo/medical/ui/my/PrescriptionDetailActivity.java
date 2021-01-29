package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.duo.medical.R;

public class PrescriptionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_detail);

        getSupportActionBar().hide();
    }
}
