package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;

public class WalletActivity extends AppCompatActivity {
    ImageView walletReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        getSupportActionBar().hide();

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
