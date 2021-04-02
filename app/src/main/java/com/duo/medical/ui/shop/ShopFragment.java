package com.duo.medical.ui.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.duo.medical.R;
import com.duo.medical.common.HorizontalListView;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopFragment extends Fragment {
    private HorizontalListView typeListView;
    private List<TypeMode> typeList;
    private RecyclerView recyclerView;
    private List<ShopMode> shopList;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    StaggeredGridLayoutManager staggeredGridLayoutManager=
                            new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
                    IndexShopAdapter indexShopAdapter=new IndexShopAdapter(ShopFragment.this,shopList);
                    indexShopAdapter.setOnItemClickListener(new IndexShopAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position, ShopMode data) {
                            Intent intent=new Intent(getActivity(),GoodsActivity.class);
                            intent.putExtra("id",data.getId()+"");
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(indexShopAdapter);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shop,container,false);

        typeListView=view.findViewById(R.id.horizontal_shop_type);
        typeModeListInit();
        MedicineTypeAdapter typeAdapter=new MedicineTypeAdapter(getContext(),R.layout.item_index_medicine_type,typeList);
        typeListView.setAdapter(typeAdapter);

        recyclerView=view.findViewById(R.id.shop_list);
        shopsInit();
        StaggeredGridLayoutManager staggeredGridLayoutManager=
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        return view;
    }

    public void typeModeListInit(){
        typeList=new ArrayList<>();
        typeList.add(new TypeMode(1,R.drawable.ic_internal_medicine,"内科"));
        typeList.add(new TypeMode(2,R.drawable.ic_surgery,"外科"));
        typeList.add(new TypeMode(3,R.drawable.ic_standby_drug,"常备"));
        typeList.add(new TypeMode(4,R.drawable.ic_chinese_medicine,"中药"));
        typeList.add(new TypeMode(5,R.drawable.ic_bisexual,"两性"));
        typeList.add(new TypeMode(6,R.drawable.ic_birth,"育儿"));
        typeList.add(new TypeMode(7,R.drawable.ic_head,"心脑"));
    }
    public void shopsInit(){
        String url="shop/consumer/index";
        NetClient.getNetClient().callNet(url,"GET",null,new NetClient.MyCallBack(){
            @Override
            public void onFailure(int code){

            }
            @Override
            public void onResponse(String json){
                System.out.println(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONArray jsonArray=new JSONArray(data);
                    int len=jsonArray.length();
                    shopList=new ArrayList<>();
                    for(int i=0;i<len;i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        int id=Integer.parseInt(jsonObject1.optString("goodsId"));
                        String img_url=jsonObject1.optString("goodsImg");
                        String desc=jsonObject1.optString("goodsName");
                        String price="￥"+jsonObject1.optString("wholesalePrice");
                        shopList.add(new ShopMode(id,img_url,desc,price));

                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    Log.e("json转换异常",e.getMessage());
                }
            }
        });
    }
}
