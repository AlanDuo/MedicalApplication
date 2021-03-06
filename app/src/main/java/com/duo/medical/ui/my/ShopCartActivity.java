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

public class ShopCartActivity extends AppCompatActivity {
    List<ShopCartMode> shopCartList;
    ListView shopCartListView;
    ImageView shopCartReturn;

    private ShopCartAdapter shopCartAdapter;

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
        shopCartAdapter=new ShopCartAdapter(ShopCartActivity.this,R.layout.item_shop_cart,shopCartList,itemOnclickInterface);
        shopCartListView.setAdapter(shopCartAdapter);

    }
    public void shopCartInit(){
        shopCartList=new ArrayList<>();
        shopCartList.add(new ShopCartMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","加厚版","20",20,-1));
        shopCartList.add(new ShopCartMode(2,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2652448203,3232878993&fm=26&gp=0.jpg","云南白药","套装版","20",20,-1));
        shopCartList.add(new ShopCartMode(3,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","加厚版","20",20,-1));
        shopCartList.add(new ShopCartMode(4,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2652448203,3232878993&fm=26&gp=0.jpg","云南白药","套装版","20",20,-1));

    }
    ShopCartAdapter.ItemOnclickInterface itemOnclickInterface=new ShopCartAdapter.ItemOnclickInterface() {
        @Override
        public void add(int position) {
            ShopCartMode mode=shopCartList.get(position);
            mode.setShopAmount(mode.getShopAmount()+1);
            shopCartAdapter.notifyDataSetChanged();
        }
        @Override
        public void decrease(int position){
            ShopCartMode mode=shopCartList.get(position);
            mode.setShopAmount(mode.getShopAmount()-1);
            shopCartAdapter.notifyDataSetChanged();
        }
        @Override
        public void changeImg(int position){
            ShopCartMode mode=shopCartList.get(position);
            mode.setSelect(-1*mode.getSelect());
            shopCartAdapter.notifyDataSetChanged();
        }
    };
}
