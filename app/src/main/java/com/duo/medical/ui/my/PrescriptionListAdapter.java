package com.duo.medical.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.R;
import com.duo.medical.common.GlideRoundTransform;
import com.duo.medical.ui.encyclopedias.EncyclopediasListAdapter;

import java.util.List;

public class PrescriptionListAdapter extends ArrayAdapter<PrescriptionListMode> {
    private List<PrescriptionListMode> mData;
    private Context mContext;
    private int resourceId;

    public PrescriptionListAdapter(Context context, int resourceId, List<PrescriptionListMode> mData) {
        super(context, resourceId, mData);
        this.mData = mData;
        this.mContext = mContext;
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        PrescriptionListMode prescriptionMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ImageView doctorImg=view.findViewById(R.id.iv_prescription_doctor_image);
        TextView doctorName=view.findViewById(R.id.tv_prescription_doctor_name);
        TextView doctorCompany=view.findViewById(R.id.tv_prescription_doctor_company);
        TextView doctorResult=view.findViewById(R.id.tv_prescription_result);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round) //预加载图片
                .error(R.drawable.ic_launcher_foreground) //加载失败图片
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new GlideRoundTransform(50)); //圆角
        Glide.with(view).load(prescriptionMode.getDoctorImg()).apply(options).into(doctorImg);
        doctorName.setText(prescriptionMode.getDoctorName());
        doctorCompany.setText(prescriptionMode.getDoctorCompany());
        doctorResult.setText(prescriptionMode.getResult());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.doctorImg=view.findViewById(R.id.iv_prescription_doctor_image);
            viewHolder.doctorName=view.findViewById(R.id.tv_prescription_doctor_name);
            viewHolder.doctorCompany=view.findViewById(R.id.tv_prescription_doctor_company);
            viewHolder.doctorResult=view.findViewById(R.id.tv_prescription_result);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(prescriptionMode.getDoctorImg()).apply(options).into(viewHolder.doctorImg);
        viewHolder.doctorName.setText(prescriptionMode.getDoctorName());
        viewHolder.doctorCompany.setText(prescriptionMode.getDoctorCompany());
        viewHolder.doctorResult.setText(prescriptionMode.getResult());
        return view;
    }

    class ViewHolder{
        ImageView doctorImg;
        TextView doctorName;
        TextView doctorCompany;
        TextView doctorResult;
    }
}
