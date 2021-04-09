package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionActivity extends AppCompatActivity {
    ListView prescriptionListView;
    List<PrescriptionListMode> prescriptionList;
    ImageView prescriptionReturn;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    PrescriptionListAdapter prescriptionListAdapter=new PrescriptionListAdapter(PrescriptionActivity.this,R.layout.item_prescription,prescriptionList);
                    prescriptionListView.setAdapter(prescriptionListAdapter);
                    prescriptionListView.setOnItemClickListener(new ListView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(PrescriptionActivity.this,PrescriptionDetailActivity.class);
                            intent.putExtra("prescriptionId",prescriptionList.get(position).getmId()+"");
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        getSupportActionBar().hide();

        prescriptionReturn=findViewById(R.id.iv_prescription_return);
        prescriptionReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrescriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        prescriptionListView=findViewById(R.id.lv_my_prescription);
        prescriptionListInit();

    }
    public void prescriptionListInit(){
        prescriptionList=new ArrayList<>();
        String prescriptionListUrl="user/health/prescriptionList";
        NetClient.getNetClient().callNet(prescriptionListUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONArray jsonArray=new JSONArray(data);
                    int len=jsonArray.length();
                    for(int i=0;i<len;i++){
                        int mId=Integer.parseInt(jsonArray.getJSONObject(i).getString("prescriptionId"));
                        String doctorImg=jsonArray.getJSONObject(i).getString("userImg");
                        String doctorName=jsonArray.getJSONObject(i).getString("username");
                        String doctorCompany=jsonArray.getJSONObject(i).getString("hospital")+"·"+jsonArray.getJSONObject(i).getString("category");
                        String result=jsonArray.getJSONObject(i).getString("diagnosisResult");
                        prescriptionList.add(new PrescriptionListMode(mId,doctorImg,doctorName,doctorCompany,result));
                    }

                }catch (Exception e){
                    Log.e("json转换异常",e.getStackTrace()+"");
                }
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        });

//        prescriptionList.add(new PrescriptionListMode(1,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎"));
//        prescriptionList.add(new PrescriptionListMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","桂林附属医院 · 呼吸外科","新冠肺炎"));

    }
}
