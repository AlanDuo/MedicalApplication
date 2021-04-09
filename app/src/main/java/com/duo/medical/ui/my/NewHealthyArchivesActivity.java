package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.ArcMotion;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

public class NewHealthyArchivesActivity extends AppCompatActivity {
    HealthyArchivesDetailMode recordDetailMode;
    ImageView newArchivesReturnImg;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_healthy_archives);

        getSupportActionBar().hide();
        newArchivesReturnImg=findViewById(R.id.iv_new_archives_return);
        newArchivesReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewHealthyArchivesActivity.this,HealthyArchivesActivity.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        if(null!=intent){
            String recordId=intent.getStringExtra("recordId");
            String recordUrl="user/health/healthRecordDetail/"+recordId;
            NetClient.getNetClient().callNet(recordUrl, "GET", null, new NetClient.MyCallBack() {
                @Override
                public void onFailure(int code) {

                }

                @Override
                public void onResponse(String json) {
                    Log.d("返回数据：",json);
                    try{
                        JSONObject jsonObject=new JSONObject(json);
                        String data=jsonObject.getString("data");
                        JSONObject dataJson=new JSONObject(data);
                        String recordId=dataJson.getString("recordId");
                        String username=dataJson.getString("username");
                        String userImg=dataJson.getString("userImg");
                        String gender=dataJson.getString("gender");
                        String age=dataJson.getString("age");
                        String relationship=dataJson.getString("relationship");
                        String diagnosisName=dataJson.getString("diagnosisName");
                        String inTime=dataJson.getString("inTime");
                        String outTime=dataJson.getString("outTime");
                        String prescription=dataJson.getString("prescription");
                        String remarks=dataJson.getString("remarks");
                        String visitHospital=dataJson.getString("visitHospital");
                        String treatmentDept=dataJson.getString("treatmentDept");
                        String doctor=dataJson.getString("doctor");
                        String phone=dataJson.getString("phone");

                        recordDetailMode=new HealthyArchivesDetailMode(recordId,username,userImg,gender,age,relationship,diagnosisName,inTime,outTime,prescription,remarks,visitHospital,treatmentDept,doctor,phone);
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);

                    }catch (Exception e){
                        Log.e("json转换异常",e.getMessage());
                    }
                }
            });
        }

    }
}
