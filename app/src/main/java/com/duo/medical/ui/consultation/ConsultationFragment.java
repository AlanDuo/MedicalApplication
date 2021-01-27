package com.duo.medical.ui.consultation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.duo.medical.R;

import java.util.ArrayList;
import java.util.List;

public class ConsultationFragment extends Fragment {
    private ListView doctorListView;
    private List<DoctorListMode> doctorList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_consultation,container,false);

        doctorListView=view.findViewById(R.id.lv_doctor_re_list);
        doctorListInit();
        DoctorListAdapter doctorListAdapter=new DoctorListAdapter(getContext(),R.layout.item_doctor_intro,doctorList);
        doctorListView.setAdapter(doctorListAdapter);
        return view;
    }

    public void doctorListInit(){
        doctorList=new ArrayList<>();
        doctorList.add(new DoctorListMode(1,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(3,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(4,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(5,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));
        doctorList.add(new DoctorListMode(6,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3805721873,3379516022&fm=26&gp=0.jpg","刘医生","副主任医师","桂林附属医院 · 呼吸外科","啥也不会，干啥啥不行"));


    }
}
