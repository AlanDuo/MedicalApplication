package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionActivity extends AppCompatActivity {
    ListView prescriptionListView;
    List<PrescriptionListMode> prescriptionList;
    ImageView prescriptionReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        getSupportActionBar().hide();

        prescriptionReturn=findViewById(R.id.iv_prescription_return);
        prescriptionReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrescriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        prescriptionListView=findViewById(R.id.lv_my_prescription);
        prescriptionListInit();
        PrescriptionListAdapter prescriptionListAdapter=new PrescriptionListAdapter(PrescriptionActivity.this,R.layout.item_prescription,prescriptionList);
        prescriptionListView.setAdapter(prescriptionListAdapter);

    }
    public void prescriptionListInit(){
        prescriptionList=new ArrayList<>();
        prescriptionList.add(new PrescriptionListMode(1,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎"));
        prescriptionList.add(new PrescriptionListMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎"));

    }
}
