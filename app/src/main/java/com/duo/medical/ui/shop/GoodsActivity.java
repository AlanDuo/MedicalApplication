package com.duo.medical.ui.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.duo.medical.MainActivity;
import com.duo.medical.R;

public class GoodsActivity extends AppCompatActivity {
    ImageView goodsImage;
    ImageView returnImage;
    ImageView goodsIntro;

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
        Glide.with(this).load("https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh=450,600/sign=7c537fa906f3d7ca0ca33772c72f923f/aec379310a55b3190fb5bae249a98226cefc17dc.jpg").into(goodsImage);

        goodsIntro=findViewById(R.id.iv_goods_intro);
        Glide.with(this).load("https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh=450,600/sign=7c537fa906f3d7ca0ca33772c72f923f/aec379310a55b3190fb5bae249a98226cefc17dc.jpg").into(goodsIntro);
    }
}
