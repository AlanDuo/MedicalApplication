package com.duo.medical.ui.encyclopedias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONObject;

public class EncyclopediasDetailsActivity extends AppCompatActivity {
    private WebView webView;
    String htmlCode=null;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    WebSettings webSettings=webView.getSettings();
                    webSettings.setTextZoom(300);
                    webSettings.setSupportZoom(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);

                    webView.setWebViewClient(new WebViewClient(){
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            // 得到 URL 可以传给应用中的某个 WebView 页面加载显示
                            return true;
                        }
                    });
                    webView.loadDataWithBaseURL(null,HtmlFormat.getNewContent(htmlCode),"text/html","UTF-8",null);
                    webSettings.setDisplayZoomControls(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedias_details);
        getSupportActionBar().hide();
        webView=findViewById(R.id.wv_news_details);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String detailUrl="encyclopedias/news/detail/"+id;
        NetClient.getNetClient().callNet(detailUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONObject object=new JSONObject(data);
                    htmlCode=object.getString("infoContent");
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
