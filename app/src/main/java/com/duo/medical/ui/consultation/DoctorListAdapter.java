package com.duo.medical.ui.consultation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duo.medical.R;
import com.duo.medical.ui.encyclopedias.EncyclopediasListAdapter;

import java.util.List;

public class DoctorListAdapter extends ArrayAdapter<DoctorListMode> {
    private List<DoctorListMode> mData;
    private Context mContext;
    private int resourceId;

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
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ImageView ivDoctorImage=view.findViewById(R.id.iv_list_doctor_img);
        TextView tvDoctorName=view.findViewById(R.id.tv_list_doctor_name);
        TextView tvDoctorLevel=view.findViewById(R.id.tv_list_doctor_level);
        TextView tvDoctorCompany=view.findViewById(R.id.tv_list_doctor_company);
        TextView tvDoctorGood=view.findViewById(R.id.tv_list_doctor_good);

        Glide.with(view).load(doctorMode.getDoctorImg()).into(ivDoctorImage);
        tvDoctorName.setText(doctorMode.getDoctorName());
        tvDoctorLevel.setText(doctorMode.getDoctorLevel());
        tvDoctorCompany.setText(doctorMode.getDoctorCompany());
        tvDoctorGood.setText(doctorMode.getDoctorGood());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.ivDoctorImage=view.findViewById(R.id.iv_list_doctor_img);
            viewHolder.tvDoctorName=view.findViewById(R.id.tv_list_doctor_name);
            viewHolder.tvDoctorLevel=view.findViewById(R.id.tv_list_doctor_level);
            viewHolder.tvDoctorCompany=view.findViewById(R.id.tv_list_doctor_company);
            viewHolder.tvDoctorGood=view.findViewById(R.id.tv_list_doctor_good);
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
        return view;

    }
    class ViewHolder{
        ImageView ivDoctorImage;
        TextView tvDoctorName;
        TextView tvDoctorLevel;
        TextView tvDoctorCompany;
        TextView tvDoctorGood;
    }
}
