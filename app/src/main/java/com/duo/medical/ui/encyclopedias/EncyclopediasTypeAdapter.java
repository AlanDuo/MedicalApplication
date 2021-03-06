package com.duo.medical.ui.encyclopedias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duo.medical.R;

import java.util.List;

public class EncyclopediasTypeAdapter extends ArrayAdapter<EncyclopediasTypeMode> {
    private List<EncyclopediasTypeMode> mData;
    private Context mContext;
    private int resourceId;

    public EncyclopediasTypeAdapter(Context context , int resourceId , List<EncyclopediasTypeMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.mData=data;
        this.resourceId=resourceId;
    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        EncyclopediasTypeMode typeMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);

        ImageView ivImage=view.findViewById(R.id.iv_encyclopedias_type_image);
        TextView tvName=view.findViewById(R.id.tv_encyclopedias_type_name);

        tvName.setText(typeMode.getmName());
        ivImage.setImageResource(typeMode.getmImg());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.ivImage=view.findViewById(R.id.iv_encyclopedias_type_image);
            viewHolder.tvName=view.findViewById(R.id.tv_encyclopedias_type_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.tvName.setText(typeMode.getmName());
        viewHolder.ivImage.setImageResource(typeMode.getmImg());

        return view;

    }
    class ViewHolder{
        ImageView ivImage;
        TextView tvName;
    }
}