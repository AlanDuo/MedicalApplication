package com.duo.medical.ui.consultation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duo.medical.R;
import com.duo.medical.ui.encyclopedias.EncyclopediasListAdapter;

import java.util.List;

public class DoctorListAdapter extends ArrayAdapter<DoctorListMode> {
    private List<DoctorListMode> mData;
    private Context mContext;
    private int resourceId;
    private ViewHolder viewHolder;

    public DoctorListAdapter(Context context,int resourcesId,List<DoctorListMode> data){
        super(context,resourcesId,data);
        this.mContext=context;
        this.resourceId=resourcesId;
        this.mData=data;
    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        DoctorListMode doctorMode=getItem(position);
        View view;

        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.ivDoctorImage=view.findViewById(R.id.iv_list_doctor_img);
            viewHolder.tvDoctorName=view.findViewById(R.id.tv_list_doctor_name);
            viewHolder.tvDoctorLevel=view.findViewById(R.id.tv_list_doctor_level);
            viewHolder.tvDoctorCompany=view.findViewById(R.id.tv_list_doctor_company);
            viewHolder.tvDoctorGood=view.findViewById(R.id.tv_list_doctor_good);
            viewHolder.btDoctorIntro=view.findViewById(R.id.bt_list_doctor_intro);
            viewHolder.btDoctorAsk=view.findViewById(R.id.bt_list_doctor_ask);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(doctorMode.getDoctorImg()).into(viewHolder.ivDoctorImage);
        viewHolder.tvDoctorName.setText(doctorMode.getDoctorName());
        viewHolder.tvDoctorLevel.setText(doctorMode.getDoctorLevel());
        viewHolder.tvDoctorCompany.setText(doctorMode.getDoctorCompany());
        viewHolder.tvDoctorGood.setText(doctorMode.getDoctorGood());
        viewHolder.btDoctorIntro.setOnClickListener(new ButtonListener(position));
        viewHolder.btDoctorAsk.setOnClickListener(new ButtonListener(position));
        return view;

    }
    class ViewHolder{
        ImageView ivDoctorImage;
        TextView tvDoctorName;
        TextView tvDoctorLevel;
        TextView tvDoctorCompany;
        TextView tvDoctorGood;
        Button btDoctorIntro;
        Button btDoctorAsk;
    }
    class ButtonListener implements View.OnClickListener{
        private int position;

        public ButtonListener(int pos){this.position=pos;}
        @Override
        public void onClick(View v){
            int vId=v.getId();
            if(vId==viewHolder.btDoctorIntro.getId()){
                Intent intent=new Intent(mContext,DoctorIntroActivity.class);
                mContext.startActivity(intent);
            }
            if(vId==viewHolder.btDoctorAsk.getId()){
                Toast.makeText(mContext,"咨询按钮",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
