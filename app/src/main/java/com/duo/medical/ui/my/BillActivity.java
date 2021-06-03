package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity {
    List<BillMode> billModeList;
    ListView listView;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BillAdapter billAdapter=new BillAdapter(BillActivity.this,R.layout.item_bill,billModeList);
                    listView.setAdapter(billAdapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        getSupportActionBar().hide();

        listView=findViewById(R.id.lv_bill_list);

        ImageView ivBillReturn=findViewById(R.id.iv_bill_return);
        ivBillReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BillActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });
        String billUrl="user/wallet/bill?userId="+MyFragment.userId;
        NetClient.getNetClient().callNet(billUrl, "GET", null, new NetClient.MyCallBack() {
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
                    billModeList=new ArrayList<>();
                    for(int i=0;i<len;i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        Long billId=Long.parseLong(object.getString("billId"));
                        String money=object.getString("money");
                        String purpose=object.getString("purpose");
                        String inOrOut=object.getString("intOrOut");
                        String billTime=object.getString("billTime");
                        if("1".equals(inOrOut)){
                            inOrOut="充值";
                        }else{
                            inOrOut="支出";
                        }
                        BillMode billMode=new BillMode(billId,money,purpose,inOrOut,billTime);
                        billModeList.add(billMode);
                    }
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
