package com.duo.medical.ui.consultation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.duo.medical.R;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsultationFragment extends Fragment {
    private ListView doctorListView;
    private List<DoctorListMode> doctorList;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    doctorListView.removeAllViewsInLayout();
                    DoctorListAdapter doctorListAdapter=new DoctorListAdapter(getContext(),R.layout.item_doctor_intro,doctorList);
                    doctorListView.setAdapter(doctorListAdapter);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_consultation,container,false);

        doctorListView=view.findViewById(R.id.lv_doctor_re_list);
        doctorListInit("");

        ImageView ivObstetricsGynecologyDept=view.findViewById(R.id.iv_obstetrics_gynecology_dept);
        ivObstetricsGynecologyDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("妇产科");
            }
        });
        ImageView ivEntDept=view.findViewById(R.id.iv_ent_dept);
        ivEntDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("五官科");
            }
        });
        ImageView ivGeneralInternalDept=view.findViewById(R.id.iv_general_internal_dept);
        ivGeneralInternalDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("普通内科");
            }
        });
        ImageView ivMouthDept=view.findViewById(R.id.iv_mouth_dept);
        ivMouthDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("口腔科");
            }
        });
        ImageView ivBreathDept=view.findViewById(R.id.iv_breath_dept);
        ivBreathDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("呼吸外科");
            }
        });
        ImageView ivEyeDept=view.findViewById(R.id.iv_eye_dept);
        ivEyeDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("眼科");
            }
        });
        ImageView ivLiverDept=view.findViewById(R.id.iv_liver_dept);
        ivLiverDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorListInit("肝胆内科");
            }
        });
        return view;
    }

    public void doctorListInit(String outpatient){
        doctorList=new ArrayList<>();
        String doctorListUrl="consultation/consultation/doctorRecommend?outpatient="+outpatient;
        NetClient.getNetClient().callNet(doctorListUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("返回数据：",json);
                try{
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    if(null!=data && !"null".equals(data)) {
                        JSONArray jsonArray = new JSONArray(data);
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            int doctorId = Integer.parseInt(object.getString("doctorId"));
                            int userId = Integer.parseInt(object.getString("userId"));
                            String doctorImg = object.getString("userImg");
                            String doctorName = object.getString("username");
                            String doctorLevel = object.getString("level");
                            String doctorCompany = object.getString("hospital") + "·" + object.getString("category");
                            String doctorGood = object.getString("goodAt");
                            doctorList.add(new DoctorListMode(userId, doctorId, doctorImg, doctorName, doctorLevel, doctorCompany, doctorGood));
                        }
                    }
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        /*doctorList.add(new DoctorListMode(1,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(3,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(4,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(5,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(6,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        Message message=new Message();
        message.what=1;
        handler.sendMessage(message);*/

    }
}
