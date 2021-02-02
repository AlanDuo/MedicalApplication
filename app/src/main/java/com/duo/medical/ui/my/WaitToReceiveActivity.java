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

public class WaitToReceiveActivity extends AppCompatActivity {
    List<ShopOrderListMode> waitToReceiveList;
    ListView waitToReceiveListView;
    ImageView waitToReceiveReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_receive);

        getSupportActionBar().hide();
        waitToReceiveListView=findViewById(R.id.lv_wait_to_receive_list);
        waitToReceiveListInit();
        WaitToReceiveAdapter receiveAdapter=new WaitToReceiveAdapter(WaitToReceiveActivity.this,R.layout.item_wait_to_receive,waitToReceiveList);
        waitToReceiveListView.setAdapter(receiveAdapter);

        waitToReceiveReturn=findViewById(R.id.iv_wait_to_receive_return);
        waitToReceiveReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToReceiveActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void waitToReceiveListInit(){
        waitToReceiveList=new ArrayList<>();
        waitToReceiveList.add(new ShopOrderListMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
        waitToReceiveList.add(new ShopOrderListMode(2,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));

    }
}
