package com.duo.medical.ui.consultation;

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
import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

public class DoctorIntroActivity extends AppCompatActivity {
    ImageView doctorIntroReturn;
    DoctorIntroMode doctorIntroMode;

    ImageView ivDoctorIntroImg;
    TextView tvDoctorIntroName;
    TextView tvDoctorIntroLevel;
    TextView tvDoctorIntroCategory;
    TextView tvDoctorIntroHospital;
    TextView tvDoctorIntroVisit;
    TextView tvDoctorIntroPraise;
    TextView tvDoctorIntroGood;
    TextView tvDoctorIntroDetail;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Glide.with(DoctorIntroActivity.this).load(doctorIntroMode.getUserImg()).into(ivDoctorIntroImg);
                    tvDoctorIntroName.setText(doctorIntroMode.getUsername());
                    tvDoctorIntroLevel.setText(doctorIntroMode.getLevel());
                    tvDoctorIntroCategory.setText(doctorIntroMode.getCategory());
                    tvDoctorIntroHospital.setText(doctorIntroMode.getHospital());
                    //tvDoctorIntroVisit.setText(doctorIntroMode.get);
                    //tvDoctorIntroPraise=;
                    tvDoctorIntroGood.setText(doctorIntroMode.getGoodAt());
                    tvDoctorIntroDetail.setText(doctorIntroMode.getIntro());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_intro);

        getSupportActionBar().hide();

        doctorIntroReturn=findViewById(R.id.iv_doctor_intro_return);
        doctorIntroReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(DoctorIntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ivDoctorIntroImg=findViewById(R.id.iv_doctor_intro_img);
        tvDoctorIntroName=findViewById(R.id.tv_doctor_intro_name);
        tvDoctorIntroLevel=findViewById(R.id.tv_doctor_intro_level);
        tvDoctorIntroCategory=findViewById(R.id.tv_doctor_intro_category);
        tvDoctorIntroHospital=findViewById(R.id.tv_doctor_intro_hospital);
        tvDoctorIntroVisit=findViewById(R.id.tv_doctor_intro_visit);
        tvDoctorIntroPraise=findViewById(R.id.tv_doctor_intro_praise);
        tvDoctorIntroGood=findViewById(R.id.tv_doctor_intro_good);
        tvDoctorIntroDetail=findViewById(R.id.tv_doctor_intro_detail);

        Intent intent=getIntent();
        String doctorId=intent.getStringExtra("doctorId");
        String introUrl="consultation/doctorIntro/"+doctorId;
        NetClient.getNetClient().callNet(introUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONObject object=new JSONObject(data);
                    String doctorId=object.getString("doctorId");
                    String username=object.getString("username");
                    String userImg=object.getString("userImg");
                    String hospital=object.getString("hospital");
                    String category=object.getString("category");
                    String level=object.getString("level");
                    String material=object.getString("material");
                    String intro=object.getString("intro");
                    String star=object.getString("star");
                    String goodAt=object.getString("goodAt");

                    doctorIntroMode=new DoctorIntroMode(doctorId,username,userImg,hospital,category,level,material,intro,star,goodAt);

                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
