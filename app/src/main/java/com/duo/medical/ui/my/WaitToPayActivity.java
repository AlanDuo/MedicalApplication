package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;

import java.util.ArrayList;
import java.util.List;

public class WaitToPayActivity extends AppCompatActivity {
    List<ShopOrderListMode> waitToPayList;
    ListView waitToPayListView;
    ImageView waitToPayReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_pay);

        getSupportActionBar().hide();

        waitToPayListView=findViewById(R.id.lv_wait_to_pay_list);
        waitToPayListInit();
        WaitToPayAdapter waitToPayAdapter=new WaitToPayAdapter(WaitToPayActivity.this,R.layout.item_wait_to_pay,waitToPayList);
        waitToPayListView.setAdapter(waitToPayAdapter);
        waitToPayListView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(WaitToPayActivity.this,WaitToPayDetailActivity.class);

                startActivity(intent);
            }
        });

        waitToPayReturn=findViewById(R.id.iv_wait_to_pay_return);
        waitToPayReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToPayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void waitToPayListInit(){
        waitToPayList=new ArrayList<>();
        waitToPayList.add(new ShopOrderListMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
        waitToPayList.add(new ShopOrderListMode(2,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));

    }
}
