package com.duo.medical.ui.my;

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

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

public class WalletActivity extends AppCompatActivity {
    ImageView walletReturn;
    TextView tvBalance;
    String wallet;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        getSupportActionBar().hide();

        tvBalance=findViewById(R.id.tv_balance);
        String walletUrl="user/wallet/balance";
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
    }
}
