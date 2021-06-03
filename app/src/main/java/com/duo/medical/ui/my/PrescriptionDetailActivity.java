package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

public class PrescriptionDetailActivity extends AppCompatActivity {
    PrescriptionDetailMode prescriptionDetailMode;

    EditText etPatientName;
    EditText etPatientGender;
    EditText etPatientAge;
    EditText etPatientSickRoom;
    EditText etPatientDate;
    TextView tvPatientResult;
    TextView tvPatientRp;
    EditText etPatientDoctor;
    EditText etPatientPrice;
    EditText etPatientDoctorPhone;
    EditText etPatientDoctorAddress;


    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    etPatientName.setText(prescriptionDetailMode.getUsername());
                    etPatientGender.setText(prescriptionDetailMode.getGender());
                    etPatientAge.setText(prescriptionDetailMode.getAge());
                    etPatientSickRoom.setText(prescriptionDetailMode.getCategory());
                    etPatientDate.setText(prescriptionDetailMode.getOrderTime());
                    tvPatientResult.setText(prescriptionDetailMode.getDiagnosisResult());
                    tvPatientRp.setText(prescriptionDetailMode.getRp());
                    etPatientDoctor.setText(prescriptionDetailMode.getDoctorName());
                    etPatientPrice.setText(prescriptionDetailMode.getMoney());
                    etPatientDoctorPhone.setText(prescriptionDetailMode.getDoctorPhone());
                    etPatientDoctorAddress.setText(prescriptionDetailMode.getHospitalAddress());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_detail);

        getSupportActionBar().hide();

        etPatientName=findViewById(R.id.et_patient_name);
        etPatientGender=findViewById(R.id.et_patient_gender);
        etPatientAge=findViewById(R.id.et_patient_age);
        etPatientSickRoom=findViewById(R.id.et_patient_sick_room);
        etPatientDate=findViewById(R.id.et_patient_date);
        tvPatientResult=findViewById(R.id.tv_patient_result);
        tvPatientRp=findViewById(R.id.tv_patient_rp);
        etPatientDoctor=findViewById(R.id.et_patient_doctor);
        etPatientPrice=findViewById(R.id.et_patient_price);
        etPatientDoctorPhone=findViewById(R.id.et_patient_doctor_phone);
        etPatientDoctorAddress=findViewById(R.id.et_patient_doctor_address);

        Intent intent=getIntent();
        String prescriptionId=intent.getStringExtra("prescriptionId");
        String prescriptionDetailUrl="user/health/prescriptionDetail/"+prescriptionId;
        NetClient.getNetClient().callNet(prescriptionDetailUrl, "GET", null, new NetClient.MyCallBack() {
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
                    String prescriptionId=dataJson.getString("prescriptionId");
                    String username=dataJson.getString("username");
                    String gender=dataJson.getString("gender");
                    if(gender.equals("1")){
                        gender="男";
                    }else{
                        gender="女";
                    }

                    String age=dataJson.getString("age");
                    String doctorName=dataJson.getString("doctorName");
                    String doctorPhone=dataJson.getString("doctorPhone");
                    String hospitalAddress=dataJson.getString("hospital");
                    String hospital=dataJson.getString("hospital");
                    String category=dataJson.getString("category");
                    String level=dataJson.getString("level");
                    String money=dataJson.getString("money");
                    String orderTime=dataJson.getString("orderTime");
                    String diagnosisResult=dataJson.getString("diagnosisResult");
                    String rp=dataJson.getString("rp");
                    prescriptionDetailMode=new PrescriptionDetailMode(prescriptionId,username,gender,age,doctorName,doctorPhone,hospitalAddress,hospital,category,level,money,orderTime,diagnosisResult,rp);

                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }catch (Exception e){
                    Log.e("json数据转换异常：",e.getMessage());
                }

            }
        });

    }
}
