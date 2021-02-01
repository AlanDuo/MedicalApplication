package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.duo.medical.R;

public class OrderActivity extends AppCompatActivity {
    ListView orderListView;
    TextView shopOrder;
    TextView consultationOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderListView=findViewById(R.id.lv_order);
        shopOrder=findViewById(R.id.tv_shop_order);
        consultationOrder=findViewById(R.id.tv_consultation_order);



    }
}
