package com.duo.medical.ui.consultation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.duo.medical.MainActivity;
import com.duo.medical.R;
import com.duo.medical.ui.my.MyFragment;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;

public class ConsultationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText et;
    private TextView tvSend;
    private String content;
    private ImageView chatReturn;
    private WebSocketClient webSocketClient;
    private String toUser;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ArrayList<ChatItemModel> data = new ArrayList<>();
            String img;
            String content;
            try {
                String msgContent=msg.obj+"";
                System.out.println(msgContent);
                String[] msgStr=msgContent.split(",");
                toUser=msgStr[0];
                img=msgStr[1];
                content=msgStr[2];
                ChatModel model2 = new ChatModel();
                model2.setIcon(img);
                model2.setContent(content);
                data.add(new ChatItemModel(ChatItemModel.CHAT_A, model2));
                adapter.addAll(data);
                hideKeyBorad(et);
            }catch(Exception e){
                e.printStackTrace();
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        getSupportActionBar().hide();

        chatReturn=findViewById(R.id.iv_chat_return);
        chatReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ConsultationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        toUser=intent.getStringExtra("userId");

        URI serverURI = URI.create("ws://192.168.43.50:9020/consultation/websocket/"+ MyFragment.userId);
        webSocketClient = new WebSocketClient(serverURI) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("连接成功");

            }
            @Override
            public void onMessage(String message) {
                Message handlerMessage = Message.obtain();
                handlerMessage.obj = message;
                handler.sendMessage(handlerMessage);
            }
            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
        webSocketClient.connect();

        recyclerView=findViewById(R.id.rv_chat);
        et=findViewById(R.id.et);
        tvSend=findViewById(R.id.tvSend);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter=new ChatAdapter());
        adapter.replaceAll(TestData.getTestAdData());
        initData();
    }
    private void initData() {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = s.toString().trim();
            }
        });

        tvSend.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                System.out.println(content);
                String message="{\"toUser\":\""+toUser+"\",\"toMessage\":\""+content+"\"}";
                webSocketClient.send(message);
                ArrayList<ChatItemModel> data = new ArrayList<>();
                ChatModel model = new ChatModel();
                model.setIcon(MyFragment.userImg);
                model.setContent(content);
                data.add(new ChatItemModel(ChatItemModel.CHAT_B, model));
                adapter.addAll(data);
                et.setText("");
                hideKeyBorad(et);
            }
        });
    }
    private void hideKeyBorad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
}
class TestData{
    public static ArrayList<ChatItemModel> getTestAdData() {
        ArrayList<ChatItemModel> models = new ArrayList<>();
       /* ChatModel model = new ChatModel();
        model.setContent("你好？请问你身体有什么问题？");
        model.setIcon("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F13%2F20200713091444_ljjlq.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618239776&t=778fdfbdc3a6cf6dc855b0591c29bc8d");
        models.add(new ChatItemModel(ChatItemModel.CHAT_A, model));
        ChatModel model2 = new ChatModel();
        model2.setContent("我的头有点疼？");
        model2.setIcon("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F13%2F20200713091444_ljjlq.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618239776&t=778fdfbdc3a6cf6dc855b0591c29bc8d");
        models.add(new ChatItemModel(ChatItemModel.CHAT_B, model2));
        ChatModel model3 = new ChatModel();
        model3.setContent("最近的睡眠怎么样");
        model3.setIcon("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F13%2F20200713091444_ljjlq.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618239776&t=778fdfbdc3a6cf6dc855b0591c29bc8d");
        models.add(new ChatItemModel(ChatItemModel.CHAT_A, model3));
        ChatModel model4 = new ChatModel();
        model4.setContent("睡得挺好的");
        model4.setIcon("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F13%2F20200713091444_ljjlq.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618239776&t=778fdfbdc3a6cf6dc855b0591c29bc8d");
        models.add(new ChatItemModel(ChatItemModel.CHAT_B, model4));
        ChatModel model5 = new ChatModel();
        model5.setContent("血压有测吗");
        model5.setIcon("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F13%2F20200713091444_ljjlq.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618239776&t=778fdfbdc3a6cf6dc855b0591c29bc8d");
        models.add(new ChatItemModel(ChatItemModel.CHAT_A, model5));
        ChatModel model6 = new ChatModel();
        model6.setContent("测了，不高，我是不是的了绝症");
        model6.setIcon("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F13%2F20200713091444_ljjlq.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618239776&t=778fdfbdc3a6cf6dc855b0591c29bc8d");
        models.add(new ChatItemModel(ChatItemModel.CHAT_B, model6));*/
        return models;
    }
}