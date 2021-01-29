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

public class HealthyArchivesActivity extends AppCompatActivity {
    ListView archivesListView;
    List<HealthyArchivesListMode> archivesList;
    ImageView archivesReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_archives);

        getSupportActionBar().hide();

        archivesReturn=findViewById(R.id.iv_archives_return);
        archivesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HealthyArchivesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        archivesListView=findViewById(R.id.lv_archives_list);
        archivesListInit();
        HealthyArchivesAdapter archivesAdapter=new HealthyArchivesAdapter(HealthyArchivesActivity.this,R.layout.item_healthy_archives,archivesList);
        archivesListView.setAdapter(archivesAdapter);

    }
    public void archivesListInit(){
        archivesList=new ArrayList<>();
        archivesList.add(new HealthyArchivesListMode(1,"脑壳疼","罗惠铎","2020-1-28"));
        archivesList.add(new HealthyArchivesListMode(2,"秃头","小明","2020-1-28"));

    }

}
