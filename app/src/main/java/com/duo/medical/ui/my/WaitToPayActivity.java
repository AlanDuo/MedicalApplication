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

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WaitToPayActivity extends AppCompatActivity {
    List<ShopOrderListMode> waitToPayList;
    ListView waitToPayListView;
    ImageView waitToPayReturn;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    WaitToPayAdapter waitToPayAdapter=new WaitToPayAdapter(WaitToPayActivity.this,R.layout.item_wait_to_pay,waitToPayList);
                    waitToPayListView.setAdapter(waitToPayAdapter);
                    waitToPayListView.setOnItemClickListener(new ListView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(WaitToPayActivity.this,WaitToPayDetailActivity.class);
                            intent.putExtra("orderId",waitToPayList.get(position).getOrderId()+"");
                            startActivity(intent);
                        }
                    });

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_pay);

        getSupportActionBar().hide();

        waitToPayListView=findViewById(R.id.lv_wait_to_pay_list);
        waitToPayListInit();

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
        String waitToPayUrl="user/order/shopOrderList?status=1";
        NetClient.getNetClient().callNet(waitToPayUrl, "GET", null, new NetClient.MyCallBack() {
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
                        waitToPayList.add(mode);
                    }
                }catch (Exception e){
                    Log.e("json转换异常",e.getMessage());
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        });
//        waitToPayList.add(new ShopOrderListMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
//        waitToPayList.add(new ShopOrderListMode(2,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));

    }
}
