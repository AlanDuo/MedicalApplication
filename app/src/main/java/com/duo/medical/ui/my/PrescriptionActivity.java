package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.duo.medical.R;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionActivity extends AppCompatActivity {
    ListView prescriptionListView;
    List<PrescriptionListMode> prescriptionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        getSupportActionBar().hide();



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
