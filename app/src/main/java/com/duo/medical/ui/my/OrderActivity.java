package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    ListView orderListView;
    TextView shopOrder;
    TextView consultationOrder;
    List<ShopOrderListMode> shopOrderList;
    List<ConsultationOrderListMode> consultationOrderList;
    ImageView orderListReturnImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().hide();

        orderListView=findViewById(R.id.lv_order);
        shopOrder=findViewById(R.id.tv_shop_order);
        consultationOrder=findViewById(R.id.tv_consultation_order);

        shopOrder.setTextColor(getResources().getColor(R.color.colorBlue));
        shopOrderInit();
        ShopOrderAdapter shopOrderAdapter=new ShopOrderAdapter(OrderActivity.this,R.layout.item_shop_order,shopOrderList);
        orderListView.setAdapter(shopOrderAdapter);

        shopOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopOrder.setTextColor(getResources().getColor(R.color.colorBlue));
                consultationOrder.setTextColor(getResources().getColor(R.color.colorTextGray));
                orderListView.removeAllViewsInLayout();
                shopOrderInit();
                ShopOrderAdapter shopOrderAdapter=new ShopOrderAdapter(OrderActivity.this,R.layout.item_shop_order,shopOrderList);
                orderListView.setAdapter(shopOrderAdapter);
            }
        });
        consultationOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultationOrder.setTextColor(getResources().getColor(R.color.colorBlue));
                shopOrder.setTextColor(getResources().getColor(R.color.colorTextGray));
                orderListView.removeAllViewsInLayout();
                consultationOrderListInit();
                ConsultationOrderAdapter consultationOrderAdapter=new ConsultationOrderAdapter(OrderActivity.this,R.layout.item_consultation_order,consultationOrderList);
                orderListView.setAdapter(consultationOrderAdapter);

            }
        });

        orderListReturnImg=findViewById(R.id.iv_shopCart_return);
        orderListReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }
    public void shopOrderInit(){
        shopOrderList=new ArrayList<>();
        shopOrderList.add(new ShopOrderListMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
        shopOrderList.add(new ShopOrderListMode(2,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
    }
    public void consultationOrderListInit(){
        consultationOrderList=new ArrayList<>();
        consultationOrderList.add(new ConsultationOrderListMode(1,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎","2021-2-2"));
        consultationOrderList.add(new ConsultationOrderListMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎","2021-2-2"));

    }
}
