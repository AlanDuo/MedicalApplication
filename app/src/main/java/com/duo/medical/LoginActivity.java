package com.duo.medical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity {
    private Boolean bPwdSwitch=false;
    private EditText etPhone;
    private EditText etPwd;
    String phone;
    String password;
    String code="500";

    public static String token=null;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if("200".equals(code)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ImageView ivPwdSwitch=findViewById(R.id.iv_pwd_switch);
        etPwd=findViewById(R.id.et_pwd);
        etPhone=findViewById(R.id.et_account);

        ivPwdSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPwdSwitch=!bPwdSwitch;
                if(bPwdSwitch){
                    ivPwdSwitch.setImageResource(
                            R.drawable.ic_visibility_black_24dp);
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    ivPwdSwitch.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    etPwd.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        Button btLogin=findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=etPhone.getText().toString();
                password=etPwd.getText().toString();
                String loginUrl="auth/oauth/token";
                RequestBody body = new FormBody.Builder()
                        .add("grant_type", "password")
                        .add("client_id", "client-app")
                        .add("client_secret","123456")
                        .add("username",phone)
                        .add("password",password)
                        .build();
                NetClient.getNetClient().callNet(loginUrl, "POST", body, new NetClient.MyCallBack() {
                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onResponse(String json) {
                        System.out.println(json);

                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            code=jsonObject.getString("code");

                            String data1=jsonObject.getString("data");
                            Log.v("data",json);
                            JSONObject jsonObject1=new JSONObject(data1);
                            token=jsonObject1.getString("token");

                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }catch (Exception e){
                            Log.v("json转换异常",e.getMessage());
                        }
                    }
                });

            }
        });
        Button btRegister=findViewById(R.id.bt_register);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
