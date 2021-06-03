package com.duo.medical.ui.my;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class WalletActivity extends AppCompatActivity {
    ImageView walletReturn;
    TextView tvBalance;
    String wallet;
    TextView tvWalletCharge;
    TextView tvBillInfo;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvBalance.setText(wallet);
                    break;
            }
        }
    };

    @SuppressLint("HandlerLeak")
    public Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvBalance.setText(wallet);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        getSupportActionBar().hide();

        tvBalance=findViewById(R.id.tv_balance);
        final Intent intent=getIntent();
        final String userId=intent.getStringExtra("userId");
        String walletUrl="user/wallet/balance?userId="+userId;
        NetClient.getNetClient().callNet(walletUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    wallet=jsonObject.getString("data");

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                }catch (Exception e){
                    Log.e("json转换异常",e.getStackTrace().toString());
                }
            }
        });

        walletReturn=findViewById(R.id.iv_wallet_return);
        walletReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WalletActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tvWalletCharge=findViewById(R.id.tv_wallet_charge);
        tvWalletCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(WalletActivity.this)
                        .setTitle("确定充值？")
                        .setMessage("本次将为您充值100元人民币")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String chargeUrl="user/wallet/chargeWallet";
                                RequestBody body=new FormBody.Builder()
                                        .add("userId",userId)
                                        .add("money","100")
                                        .add("purpose","充值")
                                        .build();
                                NetClient.getNetClient().callNet(chargeUrl, "POST", body, new NetClient.MyCallBack() {
                                    @Override
                                    public void onFailure(int code) {

                                    }

                                    @Override
                                    public void onResponse(String json) {
                                        Log.d("返回结果：",json);
                                        try{
                                            JSONObject jsonObject=new JSONObject(json);
                                            wallet=jsonObject.getString("data");

                                            Message message = new Message();
                                            message.what = 1;
                                            handler1.sendMessage(message);

                                        }catch (Exception e){
                                            Log.e("json转换异常",e.getStackTrace().toString());
                                        }
                                    }
                                });
                                Toast.makeText(WalletActivity.this, "已充值", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(WalletActivity.this, "取消充值", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        tvBillInfo=findViewById(R.id.tv_bill_info);
        tvBillInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(WalletActivity.this,BillActivity.class);
                startActivity(intent1);
            }
        });
    }
}
