package com.duo.medical.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duo.medical.R;

import java.util.List;

public class ConsultationOrderAdapter extends ArrayAdapter<ConsultationOrderListMode> {
    private List<ConsultationOrderListMode> mData;
    private Context mContext;
    private int resourceId;

    public ConsultationOrderAdapter(Context context, int resourceId, List<ConsultationOrderListMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.resourceId=resourceId;
        this.mData=data;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        ConsultationOrderListMode consultationMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ImageView orderDoctorImg = view.findViewById(R.id.iv_consultation_order_img);
        TextView orderDoctorName = view.findViewById(R.id.tv_consultation_order_doctor_name);
        TextView orderDoctorCompany = view.findViewById(R.id.tv_consultation_order_doctor_company);
        TextView orderTime=view.findViewById(R.id.tv_consultation_order_time);

        Glide.with(view).load(consultationMode.getDoctorImg()).into(orderDoctorImg);
        orderDoctorName.setText(consultationMode.getDoctorName());
        orderDoctorCompany.setText(consultationMode.getDoctorCompany());
        orderTime.setText(consultationMode.getTime());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.orderDoctorImg = view.findViewById(R.id.iv_consultation_order_img);
            viewHolder.orderDoctorName = view.findViewById(R.id.tv_consultation_order_doctor_name);
            viewHolder.orderDoctorCompany = view.findViewById(R.id.tv_consultation_order_doctor_company);
            viewHolder.orderTime=view.findViewById(R.id.tv_consultation_order_time);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(consultationMode.getDoctorImg()).into(viewHolder.orderDoctorImg);
        viewHolder.orderDoctorName.setText(consultationMode.getDoctorName());
        viewHolder.orderDoctorCompany.setText(consultationMode.getDoctorCompany());
        viewHolder.orderTime.setText(consultationMode.getTime());
        return view;

    }
    class ViewHolder{
        ImageView orderDoctorImg;
        TextView orderDoctorName;
        TextView orderDoctorCompany;
        TextView orderTime;
    }
}
