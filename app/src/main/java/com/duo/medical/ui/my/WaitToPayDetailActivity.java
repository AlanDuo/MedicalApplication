package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

public class WaitToPayDetailActivity extends AppCompatActivity {
    ImageView waitToPayDetailReturn;
    ShopOrderDetailMode orderDetailMode;

    TextView tvReceiver;
    TextView tvPhone;
    TextView tvLocation;
    ImageView ivGoodsImg;
    TextView tvGoodsName;
    TextView tvGoodsPrice;
    TextView tvAmount;
    TextView totalPrice;
    TextView tvRealPay;
    TextView tvOrderNum;
    TextView tvOrderTime;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvReceiver.setText(orderDetailMode.getUsername());
                    tvPhone.setText(orderDetailMode.getPhone());
                    tvLocation.setText(orderDetailMode.getAddress());
                    Glide.with(WaitToPayDetailActivity.this).load(orderDetailMode.getGoodsImg()).into(ivGoodsImg);
                    tvGoodsName.setText(orderDetailMode.getGoodsName());
                    tvGoodsPrice.setText(orderDetailMode.getPrice());
                    tvAmount.setText(orderDetailMode.getAmount());
                    totalPrice.setText((Integer.parseInt(orderDetailMode.getAmount())*Integer.parseInt(orderDetailMode.getPrice()))+"");
                    tvRealPay.setText((Integer.parseInt(orderDetailMode.getAmount())*Integer.parseInt(orderDetailMode.getPrice()))+"");
                    tvOrderNum.setText(orderDetailMode.getOrderNum());
                    tvOrderTime.setText(orderDetailMode.getCreateTime());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_pay_detail);

        getSupportActionBar().hide();

        tvReceiver=findViewById(R.id.tv_wait_to_pay_receiver);
        tvPhone=findViewById(R.id.tv_wait_to_pay_phone);
        tvLocation=findViewById(R.id.tv_wait_to_pay_location);
        ivGoodsImg=findViewById(R.id.iv_wait_to_pay_detail_img);
        tvGoodsName=findViewById(R.id.tv_wait_to_pay_detail_goods_name);
        tvGoodsPrice=findViewById(R.id.tv_wait_to_pay_price);
        tvAmount=findViewById(R.id.tv_wait_to_pay_detail_amount);
        totalPrice=findViewById(R.id.tv_wait_to_pay_detail_total_price);
        tvRealPay=findViewById(R.id.tv_wait_to_pay_detail_real_pay);
        tvOrderNum=findViewById(R.id.tv_wait_to_pay_order_number);
        tvOrderTime=findViewById(R.id.tv_wait_to_pay_order_time);

        waitToPayDetailReturn=findViewById(R.id.iv_wait_to_pay_detail_return);
        waitToPayDetailReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToPayDetailActivity.this,WaitToPayActivity.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        String orderId=intent.getStringExtra("orderId");
        final String orderDetailUrl="user/order/shopOrderDetail/"+orderId;
        NetClient.getNetClient().callNet(orderDetailUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONObject modeJson=new JSONObject(data);
                    String orderId=modeJson.getString("orderId");
                    String orderNum=modeJson.getString("orderNum");
                    String goodsId=modeJson.getString("goodsId");
                    String username=modeJson.getString("username");
                    String phone=modeJson.getString("phone");
                    String address=modeJson.getString("address");
                    String goodsImg=modeJson.getString("goodsImg");
                    String goodsName=modeJson.getString("goodsName");
                    String goodsDesc=modeJson.getString("goodsDesc");
                    String price=modeJson.getString("price");
                    String amount=modeJson.getString("amount");
                    String logisticsNum=modeJson.getString("logisticsNum");
                    String status=modeJson.getString("status");
                    String createTime=modeJson.getString("createTime");
                    String payTime=modeJson.getString("payTime");
                    String deliverTime=modeJson.getString("deliverTime");
                    String receiveTime=modeJson.getString("receiveTime");
                    String finishTime=modeJson.getString("finishTime");

                    orderDetailMode=new ShopOrderDetailMode(orderId,orderNum,goodsId,username,phone,address,goodsImg,goodsName,goodsDesc,price,amount,logisticsNum,status,createTime,payTime,deliverTime,receiveTime,finishTime);
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);

                }catch(Exception e){
                    Log.e("json转换异常：",e.getStackTrace()+"");
                }

            }
        });

    }
}
