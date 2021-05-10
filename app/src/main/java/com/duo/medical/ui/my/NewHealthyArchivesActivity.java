package com.duo.medical.ui.my;

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
import android.transition.ArcMotion;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.R;
import com.duo.medical.common.EncodePicToString;
import com.duo.medical.common.FileUtil;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class NewHealthyArchivesActivity extends AppCompatActivity {
    HealthyArchivesDetailMode recordDetailMode;
    ImageView newArchivesReturnImg;

    EditText etNewArchivesName;
    EditText etNewArchivesRelation;
    EditText etNewArchivesResult;
    EditText etNewArchivesInHospital;
    EditText etNewArchivesOutHospital;
    EditText etNewArchivesRemark;
    ImageView ivNewArchivesPrescription;
    EditText etNewArchivesHospital;
    EditText etNewArchivesCategory;
    EditText etNewArchivesDoctor;
    EditText etNewArchivesPhone;
    Button btNewArchivesSave;

    //判断返回到的Activity
    private static final int IMAGE_REQUEST_CODE = 0;
    String img;
    String prescriptionUrl;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    etNewArchivesName.setText(recordDetailMode.getUsername());
                    etNewArchivesRelation.setText(recordDetailMode.getRelationship());
                    etNewArchivesResult.setText(recordDetailMode.getDiagnosisName());
                    etNewArchivesInHospital.setText(recordDetailMode.getInTime());
                    etNewArchivesOutHospital.setText(recordDetailMode.getOutTime());
                    etNewArchivesRemark.setText(recordDetailMode.getRemarks());
                    Glide.with(NewHealthyArchivesActivity.this).load(recordDetailMode.getPrescription()).into(ivNewArchivesPrescription);
                    etNewArchivesHospital.setText(recordDetailMode.getVisitHospital());
                    etNewArchivesCategory.setText(recordDetailMode.getTreatmentDept());
                    etNewArchivesDoctor.setText(recordDetailMode.getDoctor());
                    etNewArchivesPhone.setText(recordDetailMode.getPhone());
                    break;
            }
        }
    };

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(NewHealthyArchivesActivity.this,"保存成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(NewHealthyArchivesActivity.this,HealthyArchivesActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_healthy_archives);

        getSupportActionBar().hide();

        etNewArchivesName=findViewById(R.id.et_new_archives_name);
        etNewArchivesRelation=findViewById(R.id.et_new_archives_relation);
        etNewArchivesResult=findViewById(R.id.et_new_archives_result);
        etNewArchivesInHospital=findViewById(R.id.et_new_archives_in_hospital);
        etNewArchivesOutHospital=findViewById(R.id.et_new_archives_out_hospital);
        etNewArchivesRemark=findViewById(R.id.et_new_archives_remark);
        ivNewArchivesPrescription=findViewById(R.id.iv_new_archives_prescription);
        etNewArchivesHospital=findViewById(R.id.et_new_archives_hospital);
        etNewArchivesCategory=findViewById(R.id.et_new_archives_category);
        etNewArchivesDoctor=findViewById(R.id.et_new_archives_doctor);
        etNewArchivesPhone=findViewById(R.id.et_new_archives_phone);
        btNewArchivesSave=findViewById(R.id.bt_new_archives_save);

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

        ivNewArchivesPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取权限
                int flag = ActivityCompat.checkSelfPermission(NewHealthyArchivesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (flag!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(NewHealthyArchivesActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                //得到权限后跳到系统相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);


            }
        });
        btNewArchivesSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etNewArchivesName.getText().toString();
                String relationship=etNewArchivesRelation.getText().toString();
                String diagnosisName=etNewArchivesResult.getText().toString();
                String inTime=etNewArchivesInHospital.getText().toString();
                String outTime=etNewArchivesOutHospital.getText().toString();
                String prescription=prescriptionUrl;
                String remarks=etNewArchivesRemark.getText().toString();
                String visitHospital=etNewArchivesHospital.getText().toString();
                String treatmentDept=etNewArchivesCategory.getText().toString();
                String doctor=etNewArchivesDoctor.getText().toString();
                String phone=etNewArchivesPhone.getText().toString();
                String newArchivesUrl="user/health/addHealthRecord";
                RequestBody body=new FormBody.Builder()
                        .add("name",name)
                        .add("relationship",relationship)
                        .add("diagnosisName",diagnosisName)
                        .add("inTime",inTime)
                        .add("outTime",outTime)
                        .add("prescription",prescription)
                        .add("remarks",remarks)
                        .add("visitHospital",visitHospital)
                        .add("treatmentDept",treatmentDept)
                        .add("doctor",doctor)
                        .add("phone",phone)
                        .add("newArchivesUrl",newArchivesUrl)
                        .build();
                NetClient.getNetClient().callNet(newArchivesUrl, "POST", body, new NetClient.MyCallBack() {
                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onResponse(String json) {
                        Log.d("返回数据：",json);
                        Message message=new Message();
                        message.what=1;
                        handler1.sendMessage(message);
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
                String filePath = FileUtil.getFilePathByUri(NewHealthyArchivesActivity.this, uri);

                img= EncodePicToString.encodePic(filePath);
                if (!TextUtils.isEmpty(filePath)) {
                    RequestOptions requestOptions1 = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                    String imguploadUrl="common/img/upload_base64";
                    RequestBody body=new FormBody.Builder()
                            .add("img",img)
                            .build();
                    NetClient.getNetClient().callNet(imguploadUrl, "POST", body, new NetClient.MyCallBack() {
                        @Override
                        public void onFailure(int code) {

                        }

                        @Override
                        public void onResponse(String json) {
                            Log.d("图片链接",json);
                            prescriptionUrl=json;
                        }
                    });
                    //将照片显示在 ivImage上
                    Glide.with(this).load(filePath).apply(requestOptions1).into(ivNewArchivesPrescription);
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
