package com.duo.medical.ui.encyclopedias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.duo.medical.R;
import com.duo.medical.common.HorizontalListView;
import com.duo.medical.common.NcovData;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EncyclopediasFragment extends Fragment {
    private HorizontalListView horizontalListView;
    private List<EncyclopediasTypeMode> typeList;
    private ListView listView;
    private List<EncyclopediasMode> encyclopediasList;

    TextView tvChineseConfirmedCount;
    TextView tvChineseIncrVoConfirmedIncr;
    TextView tvChineseCurrentConfirmedCount;
    TextView tvAbroadConfirmedCount;
    TextView tvAbroadIncrVoConfirmedIncr;
    TextView tvAbroadCurrentConfirmedCount;

    Map<String, Object> map;

    @SuppressLint("HandlerLeak")
    public Handler areaHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvChineseConfirmedCount.setText(map.get("chineseConfirmedCount")+"");
                    tvChineseIncrVoConfirmedIncr.setText(map.get("chineseIncrVoConfirmedIncr")+"");
                    tvChineseCurrentConfirmedCount.setText(map.get("chineseCurrentConfirmedCount")+"");
                    tvAbroadConfirmedCount.setText(map.get("abroadConfirmedCount")+"");
                    tvAbroadIncrVoConfirmedIncr.setText(map.get("abroadIncrVoConfirmedIncr")+"");
                    tvAbroadCurrentConfirmedCount.setText(map.get("abroadCurrentConfirmedCount")+"");
                    break;
            }
        }
    };

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    EncyclopediasListAdapter encyclopediasListAdapter=new EncyclopediasListAdapter(getContext(),R.layout.item_index_encyclopedias,encyclopediasList);
                    listView.setAdapter(encyclopediasListAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getActivity(),EncyclopediasDetailsActivity.class);
                            intent.putExtra("id",encyclopediasList.get(position).getmId()+"");
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_encyclopedias,container,false);


        horizontalListView=view.findViewById(R.id.horizontal_news_type);
        encyclopediasTypeListInit();
        EncyclopediasTypeAdapter encyclopediasTypeAdapter=new EncyclopediasTypeAdapter(getContext(),R.layout.item_encyclopedias_type,typeList);
        horizontalListView.setAdapter(encyclopediasTypeAdapter);
        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type=typeList.get(position).getmName();
                listView.removeAllViewsInLayout();
                encyclopediasListInit(type);
            }
        });

        tvChineseConfirmedCount=view.findViewById(R.id.tv_chinese_confirmedCount);
        tvChineseIncrVoConfirmedIncr=view.findViewById(R.id.tv_chinese_incrVo_confirmedIncr);
        tvChineseCurrentConfirmedCount=view.findViewById(R.id.tv_chinese_currentConfirmedCount);
        tvAbroadConfirmedCount=view.findViewById(R.id.tv_abroad_confirmedCount);
        tvAbroadIncrVoConfirmedIncr=view.findViewById(R.id.tv_abroad_incrVo_confirmedIncr);
        tvAbroadCurrentConfirmedCount=view.findViewById(R.id.tv_abroad_currentConfirmedCount);

        new Thread(new AreaDataThread()).start();

        listView=view.findViewById(R.id.news_list);
        encyclopediasListInit(null);

        final EditText etSearchNewsInput=view.findViewById(R.id.et_search_news_input);
        TextView tvSearch=view.findViewById(R.id.tv_search);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.removeAllViewsInLayout();
                String type=etSearchNewsInput.getText().toString();
                encyclopediasListInit(type);
            }
        });

        return view;
    }

    public void encyclopediasTypeListInit(){
        typeList=new ArrayList<>();
        typeList.add(new EncyclopediasTypeMode(1,R.drawable.ic_tcm_health,"中医养生"));
        typeList.add(new EncyclopediasTypeMode(2,R.drawable.ic_diet_plan,"饮食计划"));
        typeList.add(new EncyclopediasTypeMode(3,R.drawable.ic_heart_health,"心理健康"));
        typeList.add(new EncyclopediasTypeMode(4,R.drawable.ic_stomach_health,"肠胃呵护"));
        typeList.add(new EncyclopediasTypeMode(5,R.drawable.ic_cosmetology,"护肤美容"));
        typeList.add(new EncyclopediasTypeMode(6,R.drawable.ic_mouth_health,"口腔健康"));
    }
    public void encyclopediasListInit(String type){
        encyclopediasList=new ArrayList<>();
        String newsListUrl="encyclopedias/news/list?type="+type;
        NetClient.getNetClient().callNet(newsListUrl, "GET", null, new NetClient.MyCallBack() {
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
                        JSONObject mode=jsonArray.getJSONObject(i);
                        String picUrl=mode.getString("pic");
                        String desc=mode.getString("title");
                        String shareTime=mode.getString("pushTime");
                        int mId=Integer.parseInt(mode.getString("infoId"));
                        encyclopediasList.add(new EncyclopediasMode(desc,picUrl,shareTime,mId));
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
    class AreaDataThread implements Runnable{
        @Override
        public void run(){
            try {
                map = NcovData.getNcovData();
                Message message=new Message();
                message.what=1;
                areaHandler.sendMessage(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
