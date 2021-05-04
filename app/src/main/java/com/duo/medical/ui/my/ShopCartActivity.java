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

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ShopCartActivity extends AppCompatActivity {
    List<ShopCartMode> shopCartList;
    ListView shopCartListView;
    ImageView shopCartReturn;
    TextView tvCountSelected;
    ImageView ivSelectAll;
    Button btPayNow;

    private ShopCartAdapter shopCartAdapter;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    shopCartAdapter=new ShopCartAdapter(ShopCartActivity.this,R.layout.item_shop_cart,shopCartList,itemOnclickInterface);
                    shopCartListView.setAdapter(shopCartAdapter);
                    int count=0;
                    for(int i=0;i<shopCartList.size();i++){
                        if(shopCartList.get(i).getSelect()>0){
                            count+=(Integer.parseInt(shopCartList.get(i).getShopPrice())*shopCartList.get(i).getShopAmount());
                        }
                    }
                    tvCountSelected.setText("¥ "+count);
                    break;
            }
        }
    };
    @SuppressLint("HandlerLeak")
    public Handler addToWaitToPayHandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent intent=new Intent(ShopCartActivity.this,WaitToPayActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);

        getSupportActionBar().hide();

        shopCartReturn=findViewById(R.id.iv_shopCart_return);
        shopCartReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        shopCartListView=findViewById(R.id.lv_shop_cart);
        shopCartInit();
        tvCountSelected=findViewById(R.id.tv_count_selected);
        ivSelectAll=findViewById(R.id.iv_select_all);
        ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSelectAll.setImageResource(R.drawable.ic_circle_right);
                for(int i=0;i<shopCartList.size();i++){
                    shopCartList.get(i).setSelect(1);
                }
                shopCartAdapter.notifyDataSetChanged();
                shopCartAdapter=new ShopCartAdapter(ShopCartActivity.this,R.layout.item_shop_cart,shopCartList,itemOnclickInterface);
                shopCartListView.setAdapter(shopCartAdapter);
                int count=0;
                for(int i=0;i<shopCartList.size();i++){
                    if(shopCartList.get(i).getSelect()>0){
                        count+=(Integer.parseInt(shopCartList.get(i).getShopPrice())*shopCartList.get(i).getShopAmount());
                    }
                }
                tvCountSelected.setText("¥ "+count);
            }
        });
        btPayNow=findViewById(R.id.bt_pay_now);
        btPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addWaitToPayUrl="shop/order/add_wait_to_pay";
                String goodsIds="";
                String amounts="";
                int size=shopCartList.size();
                for(int i=0;i<size;i++){
                    if(shopCartList.get(i).getSelect()>0){
                        goodsIds+=shopCartList.get(i).getGoodsId()+" ";
                        amounts+=shopCartList.get(i).getShopAmount()+" ";

                    }
                }
                goodsIds=goodsIds.trim();
                amounts=amounts.trim();
                RequestBody body=new FormBody.Builder()
                        .add("goodsIds",goodsIds)
                        .add("amounts",amounts)
                        .build();
                NetClient.getNetClient().callNet(addWaitToPayUrl, "POST", body, new NetClient.MyCallBack() {
                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onResponse(String json) {
                        Log.d("返回数据：",json);
                        Message message=new Message();
                        message.what=1;
                        addToWaitToPayHandler.sendMessage(message);
                    }
                });

            }
        });

    }
    public void shopCartInit(){
        shopCartList=new ArrayList<>();
        String shopCartUrl="shop/shop_cart/shopCartList";
        NetClient.getNetClient().callNet(shopCartUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONArray jsonArray=new JSONArray(data);
                    int size=jsonArray.length();
                    for(int i=0;i<size;i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        String goodsId=object.getString("goodsId");
                        String shopImg=object.getString("goodsImg");
                        String shopDesc=object.getString("goodsDesc");
                        String shopPrice=object.getString("wholesalePrice");
                        int shopAmount=Integer.parseInt(object.getString("amount"));
                        shopCartList.add(new ShopCartMode(goodsId,shopImg,shopDesc,shopPrice,shopAmount,-1));
                    }
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    ShopCartAdapter.ItemOnclickInterface itemOnclickInterface=new ShopCartAdapter.ItemOnclickInterface() {
        @Override
        public void add(int position) {
            ShopCartMode mode=shopCartList.get(position);
            mode.setShopAmount(mode.getShopAmount()+1);
            shopCartAdapter.notifyDataSetChanged();
            int count=0;
            for(int i=0;i<shopCartList.size();i++){
                if(shopCartList.get(i).getSelect()>0){
                    count+=(Integer.parseInt(shopCartList.get(i).getShopPrice())*shopCartList.get(i).getShopAmount());
                }
            }
            tvCountSelected.setText("¥ "+count);
        }
        @Override
        public void decrease(int position){
            ShopCartMode mode=shopCartList.get(position);
            mode.setShopAmount(mode.getShopAmount()-1);
            shopCartAdapter.notifyDataSetChanged();
            int count=0;
            for(int i=0;i<shopCartList.size();i++){
                if(shopCartList.get(i).getSelect()>0){
                    count+=(Integer.parseInt(shopCartList.get(i).getShopPrice())*shopCartList.get(i).getShopAmount());
                }
            }
            tvCountSelected.setText("¥ "+count);
        }
        @Override
        public void changeImg(int position){
            ShopCartMode mode=shopCartList.get(position);
            mode.setSelect(-1*mode.getSelect());
            shopCartAdapter.notifyDataSetChanged();
            int count=0;
            for(int i=0;i<shopCartList.size();i++){
                if(shopCartList.get(i).getSelect()>0){
                    count+=(Integer.parseInt(shopCartList.get(i).getShopPrice())*shopCartList.get(i).getShopAmount());
                }
            }
            tvCountSelected.setText("¥ "+count);
        }
    };
}
