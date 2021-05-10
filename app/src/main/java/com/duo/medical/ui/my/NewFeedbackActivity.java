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

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class NewFeedbackActivity extends AppCompatActivity {
    //判断返回到的Activity
    private static final int IMAGE_REQUEST_CODE = 0;
    ImageView newFeedbackReturn;

    EditText etQuestionContent;
    EditText etQuestionContact;
    ImageView ivQuestionImg;

    String img;
    String imgUrl=null;

    Button btQuestionSubmit;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(NewFeedbackActivity.this,"反馈成功",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feedback);

        getSupportActionBar().hide();

        newFeedbackReturn=findViewById(R.id.iv_new_feedback_return);
        newFeedbackReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewFeedbackActivity.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });
        etQuestionContent=findViewById(R.id.et_question_content);
        etQuestionContact=findViewById(R.id.et_question_contact);
        ivQuestionImg=findViewById(R.id.iv_question_img);
        btQuestionSubmit=findViewById(R.id.bt_question_submit);

        ivQuestionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取权限
                int flag = ActivityCompat.checkSelfPermission(NewFeedbackActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (flag!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(NewFeedbackActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                //得到权限后跳到系统相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);


            }
        });
        btQuestionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=etQuestionContent.getText().toString();
                String contact=etQuestionContact.getText().toString();
                String feedbackUrl="user/user/feedback";
                RequestBody body=new FormBody.Builder()
                        .add("content",content)
                        .add("contact",contact)
                        .add("picture",imgUrl)
                        .build();
                NetClient.getNetClient().callNet(feedbackUrl, "POST", body, new NetClient.MyCallBack() {
                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onResponse(String json) {
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
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
                String filePath = FileUtil.getFilePathByUri(NewFeedbackActivity.this, uri);

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
                            imgUrl=json;
                        }
                    });
                    //将照片显示在 ivImage上
                    Glide.with(this).load(filePath).apply(requestOptions1).into(ivQuestionImg);
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
