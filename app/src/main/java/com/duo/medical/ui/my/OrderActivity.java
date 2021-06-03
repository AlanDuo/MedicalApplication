package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    ListView orderListView;
    TextView shopOrder;
    TextView consultationOrder;
    List<ShopOrderListMode> shopOrderList;
    List<ConsultationOrderListMode> consultationOrderList;
    ImageView orderListReturnImg;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ShopOrderAdapter shopOrderAdapter=new ShopOrderAdapter(OrderActivity.this,R.layout.item_shop_order,shopOrderList);
                    orderListView.setAdapter(shopOrderAdapter);

                    break;
            }
        }
    };


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

        shopOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopOrder.setTextColor(getResources().getColor(R.color.colorBlue));
                consultationOrder.setTextColor(getResources().getColor(R.color.colorTextGray));
                orderListView.removeAllViewsInLayout();
                shopOrderInit();

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
        String shopOrderUrl="user/order/shopOrderList/4";
        NetClient.getNetClient().callNet(shopOrderUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONArray dataArray=new JSONArray(data);
                    int len=dataArray.length();
                    for(int i=0;i<len;i++){
                        JSONObject orderJson=dataArray.getJSONObject(i);
                        int orderId=Integer.parseInt(orderJson.getString("orderId"));
                        String orderImg=orderJson.getString("goodsImg");
                        String orderDesc=orderJson.getString("goodsDesc");
                        String orderPrice=orderJson.getString("price");
                        ShopOrderListMode mode=new ShopOrderListMode(orderId,orderImg,orderDesc,orderPrice);
                        shopOrderList.add(mode);
                    }
                }catch (Exception e){
                    Log.e("json转换异常",e.getMessage());
                }
                Message message = new Message();
                message.what = 1;
                handler1.sendMessage(message);
            }
        });

        //shopOrderList.add(new ShopOrderListMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
        //shopOrderList.add(new ShopOrderListMode(2,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
    }
    public void consultationOrderListInit(){
        consultationOrderList=new ArrayList<>();
        consultationOrderList.add(new ConsultationOrderListMode(1,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎","2021-2-2"));
        consultationOrderList.add(new ConsultationOrderListMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎","2021-2-2"));

    }
}
