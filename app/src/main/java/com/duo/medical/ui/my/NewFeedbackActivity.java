package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duo.medical.R;

public class NewFeedbackActivity extends AppCompatActivity {
    ImageView newFeedbackReturn;

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
    }
}
