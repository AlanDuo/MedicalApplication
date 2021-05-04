package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

import cn.hutool.core.util.RandomUtil;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class WaitToPayDetailActivity extends AppCompatActivity {
    ImageView waitToPayDetailReturn;

    EditText etReceiver;
    EditText etPhone;
    EditText etLocation;
    ImageView ivGoodsImg;
    TextView tvGoodsName;
    TextView tvGoodsPrice;
    TextView tvAmount;
    TextView totalPrice;
    TextView tvRealPay;
    TextView tvOrderNum;

    Button btPay;

    String orderId;
    String goodsImg;
    String goodsDesc;
    String price;
    String goodsId;
    String amount;
    String goodsName;
    String orderNum;

    String username;
    String phone;
    String address;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(WaitToPayDetailActivity.this,"支付成功",Toast.LENGTH_LONG);
                    Intent intent=new Intent(WaitToPayDetailActivity.this,WaitToReceiveActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_to_pay_detail);

        getSupportActionBar().hide();

        etReceiver=findViewById(R.id.et_wait_to_pay_receiver);
        etPhone=findViewById(R.id.et_wait_to_pay_phone);
        etLocation=findViewById(R.id.et_wait_to_pay_location);
        ivGoodsImg=findViewById(R.id.iv_wait_to_pay_detail_img);
        tvGoodsName=findViewById(R.id.tv_wait_to_pay_detail_goods_name);
        tvGoodsPrice=findViewById(R.id.tv_wait_to_pay_price);
        tvAmount=findViewById(R.id.tv_wait_to_pay_detail_amount);
        totalPrice=findViewById(R.id.tv_wait_to_pay_detail_total_price);
        tvRealPay=findViewById(R.id.tv_wait_to_pay_detail_real_pay);
        tvOrderNum=findViewById(R.id.tv_wait_to_pay_order_number);
        btPay=findViewById(R.id.bt_wait_to_pay_toPay);

        waitToPayDetailReturn=findViewById(R.id.iv_wait_to_pay_detail_return);
        waitToPayDetailReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WaitToPayDetailActivity.this,WaitToPayActivity.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        orderId=intent.getStringExtra("orderId");
        goodsImg=intent.getStringExtra("goodsImg");
        goodsDesc=intent.getStringExtra("goodsDesc");
        price=intent.getStringExtra("price");
        goodsId=intent.getStringExtra("goodsId");
        amount=intent.getStringExtra("amount");
        goodsName=intent.getStringExtra("goodsName");

        Glide.with(WaitToPayDetailActivity.this).load(goodsImg).into(ivGoodsImg);
        tvGoodsName.setText(goodsDesc);
        tvGoodsPrice.setText(price);
        tvAmount.setText(amount);
        totalPrice.setText((Integer.parseInt(amount)*Integer.parseInt(price))+"");
        tvRealPay.setText((Integer.parseInt(amount)*Integer.parseInt(price))+"");
        orderNum= RandomUtil.randomString("0123456789",9);
        tvOrderNum.setText(orderNum);

        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=etReceiver.getText().toString();
                phone=etPhone.getText().toString();
                address=etLocation.getText().toString();
                String payOrderUrl="shop/order/add";
                RequestBody body=new FormBody.Builder()
                        .add("orderId",orderId)
                        .add("orderNum",orderNum)
                        .add("goodsId",goodsId)
                        .add("goodsName",goodsName)
                        .add("amount",amount)
                        .add("username",username)
                        .add("phone",phone)
                        .add("address",address)
                        .build();
                NetClient.getNetClient().callNet(payOrderUrl, "POST", body, new NetClient.MyCallBack() {
                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onResponse(String json) {
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                });
            }
        });


    }
}
