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

public class WaitToReceiveDetailActivity extends AppCompatActivity {
    ImageView receiveDetailReturnImg;
    ShopOrderDetailMode orderDetailMode;

    TextView tvReceiver;
    TextView tvPhone;
    TextView tvLocation;
    TextView tvCurrentLogistics;
    TextView tvCurrentLogisticsTime;
    ImageView ivGoodsImg;
    TextView tvGoodsName;
    TextView tvGoodsPrice;
    TextView tvAmount;
    TextView totalPrice;
    TextView tvRealPay;
    TextView tvOrderNum;
    TextView tvLogisticsNumber;
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

                    Glide.with(WaitToReceiveDetailActivity.this).load(orderDetailMode.getGoodsImg()).into(ivGoodsImg);
                    tvGoodsName.setText(orderDetailMode.getGoodsName());
                    tvGoodsPrice.setText(orderDetailMode.getPrice());
                    tvAmount.setText(orderDetailMode.getAmount());
                    totalPrice.setText((Integer.parseInt(orderDetailMode.getAmount())*Integer.parseInt(orderDetailMode.getPrice()))+"");
                    tvRealPay.setText((Integer.parseInt(orderDetailMode.getAmount())*Integer.parseInt(orderDetailMode.getPrice()))+"");
                    tvOrderNum.setText(orderDetailMode.getOrderNum());
                    tvLogisticsNumber.setText(orderDetailMode.getOrderNum());
                    tvOrderTime.setText(orderDetailMode.getCreateTime());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_receive_detail);

        getSupportActionBar().hide();

        tvReceiver=findViewById(R.id.tv_wait_to_receive_receiver);
        tvPhone=findViewById(R.id.tv_wait_to_receive_phone);
        tvLocation=findViewById(R.id.tv_wait_to_receive_location);
        tvCurrentLogistics=findViewById(R.id.tv_current_logistics);
        tvCurrentLogisticsTime=findViewById(R.id.tv_current_logistics_time);
        ivGoodsImg=findViewById(R.id.iv_wait_to_receive_detail_img);
        tvGoodsName=findViewById(R.id.tv_wait_to_receive_detail_goods_name);
        tvGoodsPrice=findViewById(R.id.tv_wait_to_receive_detail_price);
        tvAmount=findViewById(R.id.tv_wait_to_receive_detail_amount);
        totalPrice=findViewById(R.id.tv_wait_to_receive_detail_total_price);
        tvRealPay=findViewById(R.id.tv_wait_to_receive_detail_real_pay);
        tvOrderNum=findViewById(R.id.tv_wait_to_receive_order_number);
        tvLogisticsNumber=findViewById(R.id.tv_wait_to_receive_logistics_number);
        tvOrderTime=findViewById(R.id.tv_wait_to_receive_order_time);

        receiveDetailReturnImg=findViewById(R.id.iv_wait_to_receive_detail_return);
        receiveDetailReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToReceiveDetailActivity.this,WaitToReceiveActivity.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        String orderId=intent.getStringExtra("orderId");
        String waitToReceiveUrl="user/order/shopOrderDetail/"+orderId;
        NetClient.getNetClient().callNet(waitToReceiveUrl, "GET", null, new NetClient.MyCallBack() {
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
