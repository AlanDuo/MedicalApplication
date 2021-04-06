package com.duo.medical.ui.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;
import com.duo.medical.ui.my.ShopOrderDetailActivity;
import com.duo.medical.ui.my.WaitToPayActivity;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class GoodsActivity extends AppCompatActivity {
    ImageView returnImage;
    ImageView goodsImage;
    ImageView goodsIntro;
    TextView tvGoodsPrice;
    TextView tvGoodsDesc;
    TextView tvGoodsPurpose;
    TextView tvGoodsSource;
    TextView tvGoodsDeliver;
    TextView tvAllEvaluate;
    ImageView ivEvaluateUserImg;
    TextView tvEvaluateUsername;
    TextView tvEvaluateUserEvaluate;
    Button btAddShopCart;
    Button btRightBuy;

    GoodsInfoMode goodsInfoMode;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Glide.with(GoodsActivity.this).load(goodsInfoMode.getGoodsImg()).into(goodsImage);
                    tvGoodsPrice.setText(goodsInfoMode.getWholesalePrice());
                    tvGoodsDesc.setText(goodsInfoMode.getGoodsDesc());
                    tvGoodsPurpose.setText(goodsInfoMode.getGoodsPurpose());
                    tvGoodsSource.setText( goodsInfoMode.getGoodsSource());
                    tvGoodsDeliver.setText("顺丰包邮");
                    Glide.with(GoodsActivity.this).load(goodsInfoMode.getEvaUserImg()).into(ivEvaluateUserImg);
                    tvEvaluateUsername.setText(goodsInfoMode.getEvaUsername());
                    tvEvaluateUserEvaluate.setText(goodsInfoMode.getEvaContent());
                    Glide.with(GoodsActivity.this).load(goodsInfoMode.getIntroImg()).into(goodsIntro);
                    break;
            }
        }
    };
    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(GoodsActivity.this,"加入成功",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(GoodsActivity.this,"加入失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        getSupportActionBar().hide();

        returnImage=findViewById(R.id.iv_goods_return);
        returnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GoodsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        goodsImage=findViewById(R.id.iv_goods_image);
        goodsIntro=findViewById(R.id.iv_goods_intro);
        tvGoodsPrice = findViewById(R.id.tv_goods_price);
        tvGoodsDesc = findViewById(R.id.tv_goods_desc);
        tvGoodsPurpose = findViewById(R.id.tv_goods_purpose);
        tvGoodsSource = findViewById(R.id.tv_goods_source);
        tvGoodsDeliver = findViewById(R.id.tv_goods_deliver);
        tvAllEvaluate = findViewById(R.id.tv_all_evaluate);
        ivEvaluateUserImg = findViewById(R.id.iv_evaluate_user_img);
        tvEvaluateUsername = findViewById(R.id.tv_evaluate_username);
        tvEvaluateUserEvaluate = findViewById(R.id.tv_evaluate_user_evaluate);
        btAddShopCart=findViewById(R.id.bt_add_shop_cart);
        btRightBuy=findViewById(R.id.bt_right_buy);

        Intent intent=getIntent();
        final String goodsId=intent.getStringExtra("id");
        String goodsUrl="shop/consumer/goodsInfo/"+goodsId;
        NetClient.getNetClient().callNet(goodsUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }
            @Override
            public void onResponse(String json) {
                Log.d("数据详情：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONObject goodsJson=new JSONObject(data);
                    String goodsId=goodsJson.getString("goodsId");
                    String goodsName=goodsJson.getString("goodsName");
                    String goodsImg=goodsJson.getString("goodsImg");
                    String introImg=goodsJson.getString("introImg");
                    String goodsDesc=goodsJson.getString("goodsDesc");
                    String goodsType=goodsJson.getString("goodsType");
                    String goodsPurpose=goodsJson.getString("goodsPurpose");
                    String goodsSource=goodsJson.getString("goodsSource");
                    String wholesalePrice=goodsJson.getString("wholesalePrice");
                    String stock=goodsJson.getString("stock");
                    String evaUserId=goodsJson.getString("evaUserId");
                    String evaUsername=goodsJson.getString("evaUsername");
                    String evaUserImg=goodsJson.getString("evaUserImg");
                    String evaContent=goodsJson.getString("evaContent");
                    goodsInfoMode=new GoodsInfoMode(goodsId,goodsName,goodsImg,introImg,goodsDesc,goodsType,goodsPurpose,goodsSource,wholesalePrice,stock,evaUserId,evaUsername,evaUserImg,evaContent);

                    Message message = new Message();
                    message.what = 1;
                    handler1.sendMessage(message);
                }catch(Exception e){
                    Log.e("json转换异常",e.getMessage());
                }
            }
        });

        btAddShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addShopCartUrl="shop/shop_cart/addToShopCart";
                RequestBody body = new FormBody.Builder()
                        .add("goodsId",goodsId)
                        .add("amount","1")
                        .build();
                NetClient.getNetClient().callNet(addShopCartUrl,"POST",body,new NetClient.MyCallBack(){
                    @Override
                    public void onFailure(int code) {

                    }
                    @Override
                    public void onResponse(String json) {
                        Log.e("数据详情:",json);
                        try{
                            JSONObject jsonObject=new JSONObject(json);
                            String code=jsonObject.getString("code");
                            Message message = new Message();
                            if(code.equals("200")){
                                message.what = 1;
                            }else{
                                message.what = 2;
                            }
                            handler2.sendMessage(message);
                        }catch (Exception e){
                            Log.e("json转换异常：",e.getMessage());
                        }
                    }
                });
            }
        });
        btRightBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(GoodsActivity.this, WaitToPayActivity.class);
                intent1.putExtra("goodsId",goodsId);

                startActivity(intent1);
            }
        });

    }
}
