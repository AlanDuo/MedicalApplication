package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.GlideRoundTransform;

public class PersonInfoActivity extends AppCompatActivity {
    ImageView ivReturn;

    ImageView ivUserImg;
    EditText etUsername;
    EditText etPhone;
    EditText etIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        getSupportActionBar().hide();

        ivUserImg=findViewById(R.id.iv_user_img);
        etUsername=findViewById(R.id.et_username);
        etPhone=findViewById(R.id.et_phone);
        etIdCard=findViewById(R.id.et_id_card);

        ivReturn=findViewById(R.id.iv_person_return);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        String userId=intent.getStringExtra("userId");
        String phone=intent.getStringExtra("phone");
        String idCard=intent.getStringExtra("idCard");
        String userImg=intent.getStringExtra("userImg");
        String username=intent.getStringExtra("username");

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round) //预加载图片
                .error(R.drawable.ic_launcher_foreground) //加载失败图片
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new GlideRoundTransform(50)); //圆角
        Glide.with(PersonInfoActivity.this).load(userImg).apply(options).into(ivUserImg);
        etUsername.setText(username);
        etPhone.setText(phone);
        etIdCard.setText(idCard);

    }
}
