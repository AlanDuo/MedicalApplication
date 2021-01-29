package com.duo.medical.ui.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;

public class FeedBackActivity extends AppCompatActivity {
    ImageView feedbackReturn;
    TextView addFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        getSupportActionBar().hide();

        feedbackReturn=findViewById(R.id.iv_feedback_return);
        feedbackReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FeedBackActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addFeedback=findViewById(R.id.tv_question_feedback);
        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FeedBackActivity.this,NewFeedbackActivity.class);

                startActivity(intent);
            }
        });

    }
}
