package com.duo.medical;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.common.EncodePicToString;
import com.duo.medical.common.FileUtil;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends AppCompatActivity {

    //判断返回到的Activity
    private static final int IMAGE_REQUEST_CODE = 0;
    String code="500";

    ImageView ivUserImg;
    TextView etUsername;
    TextView  etPassword;
    TextView  etPhone;
    TextView  etBirthday;
    TextView  etIdCard;
    TextView etGender;
    Button bt_register;

    String userImg;
    String userImgUrl;
    String username;
    String password;
    String phone;
    String birthday;
    String idCard;
    String gender;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if("200".equals(code)) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ivUserImg=findViewById(R.id.iv_img);
        etUsername=findViewById(R.id.et_username);
        etPassword=findViewById(R.id.et_pwd);
        etPhone=findViewById(R.id.et_phone);
        etBirthday=findViewById(R.id.et_birthday);
        etIdCard=findViewById(R.id.et_id_card);
        etGender=findViewById(R.id.et_gender);

        bt_register=findViewById(R.id.rg_register);

        ivUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取权限
                int flag = ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (flag!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                //得到权限后跳到系统相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);


            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                phone=etPhone.getText().toString();
                birthday=etBirthday.getText().toString();
                idCard=etIdCard.getText().toString();
                gender=etGender.getText().toString();
                if("男".equals(gender)){
                    gender="0";
                }else{
                    gender="1";
                }
                String registerUrl="user/user/register";
                RequestBody body=new FormBody.Builder()
                        .add("userImg",userImgUrl)
                        .add("username",username)
                        .add("password",password)
                        .add("phone",phone)
                        .add("birthday",birthday)
                        .add("idCard",idCard)
                        .add("gender",gender)
                        .add("role","USER")
                        .build();
                NetClient.getNetClient().callNet(registerUrl, "POST", body, new NetClient.MyCallBack() {
                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onResponse(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            code=jsonObject.getString("code");
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case IMAGE_REQUEST_CODE:
                Uri uri = data.getData();
                String filePath = FileUtil.getFilePathByUri(RegisterActivity.this, uri);

                userImg= EncodePicToString.encodePic(filePath);
                if (!TextUtils.isEmpty(filePath)) {
                    RequestOptions requestOptions1 = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                    String imguploadUrl="common/img/upload_base64";
                    RequestBody body=new FormBody.Builder()
                            .add("img",userImg)
                            .build();
                    NetClient.getNetClient().callNet(imguploadUrl, "POST", body, new NetClient.MyCallBack() {
                        @Override
                        public void onFailure(int code) {

                        }

                        @Override
                        public void onResponse(String json) {
                            Log.d("图片链接",json);
                            userImgUrl=json;
                        }
                    });
                    //将照片显示在 ivImage上
                    Glide.with(this).load(filePath).apply(requestOptions1).into(ivUserImg);
                }
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
