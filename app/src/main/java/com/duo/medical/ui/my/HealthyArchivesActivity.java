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
import android.widget.TextView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HealthyArchivesActivity extends AppCompatActivity {
    ListView archivesListView;
    List<HealthyArchivesListMode> archivesList;
    ImageView archivesReturn;
    ImageView addRecordImg;
    TextView addRecordText;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    HealthyArchivesAdapter archivesAdapter=new HealthyArchivesAdapter(HealthyArchivesActivity.this,R.layout.item_healthy_archives,archivesList);
                    archivesListView.setAdapter(archivesAdapter);
                    archivesListView.setOnItemClickListener(new ListView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(HealthyArchivesActivity.this,NewHealthyArchivesActivity.class);
                            intent.putExtra("recordId",archivesList.get(position).getmId()+"");
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
        setContentView(R.layout.activity_healthy_archives);

        getSupportActionBar().hide();

        archivesReturn=findViewById(R.id.iv_archives_return);
        archivesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HealthyArchivesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        archivesListView=findViewById(R.id.lv_archives_list);
        archivesListInit();

        addRecordImg=findViewById(R.id.iv_archives_add);
        addRecordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HealthyArchivesActivity.this,NewHealthyArchivesActivity.class);
                startActivity(intent);
            }
        });
        addRecordText=findViewById(R.id.tv_archives_add);
        addRecordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HealthyArchivesActivity.this,NewHealthyArchivesActivity.class);
                startActivity(intent);
            }
        });
    }
    public void archivesListInit(){
        archivesList=new ArrayList<>();
        String archivesUrl="user/health/healthRecordList";
        NetClient.getNetClient().callNet(archivesUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回的数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONArray jsonArray=new JSONArray(data);
                    int len=jsonArray.length();
                    for(int i=0;i<len;i++) {
                        JSONObject mode=jsonArray.getJSONObject(i);
                        int mId=Integer.parseInt(mode.getString("recordId"));
                        String sickName=mode.getString("diagnosisName");
                        String patient=mode.getString("userName")+"("+mode.getString("relationship")+")";
                        String recordDate=mode.getString("inTime");
                        archivesList.add(new HealthyArchivesListMode(mId,sickName,patient,recordDate));
                    }
                }catch (Exception e){
                    Log.e("json转换异常",e.getStackTrace()+"");
                }
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        });
//        archivesList.add(new HealthyArchivesListMode(1,"脑壳疼","罗惠铎","2020-1-28"));
//        archivesList.add(new HealthyArchivesListMode(2,"秃头","小明","2020-1-28"));

    }

}
