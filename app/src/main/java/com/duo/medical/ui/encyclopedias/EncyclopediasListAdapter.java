package com.duo.medical.ui.encyclopedias;

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

public class EncyclopediasListAdapter extends ArrayAdapter<EncyclopediasMode> {
    private List<EncyclopediasMode> mData;
    private Context mContext;
    private int resourceId;

    public EncyclopediasListAdapter(Context context,int resourcesId,List<EncyclopediasMode> data){
        super(context,resourcesId,data);
        this.mContext=context;
        this.resourceId=resourcesId;
        this.mData=data;
    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        EncyclopediasMode encyclopediasMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ImageView ivImage=view.findViewById(R.id.iv_news_image);
        TextView tvDesc=view.findViewById(R.id.tv_news_desc);
        TextView tvShareTime=view.findViewById(R.id.tv_news_shareTime);

        tvDesc.setText(encyclopediasMode.getDesc());
        tvShareTime.setText(encyclopediasMode.getShareTime());

        Glide.with(view).load(encyclopediasMode.getPicUrl()).into(ivImage);

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.ivImage=view.findViewById(R.id.iv_news_image);
            viewHolder.tvDesc=view.findViewById(R.id.tv_news_desc);
            viewHolder.tvShareTime=view.findViewById(R.id.tv_news_shareTime);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.tvDesc.setText(encyclopediasMode.getDesc());
        viewHolder.tvShareTime.setText(encyclopediasMode.getShareTime());
        Glide.with(view).load(encyclopediasMode.getPicUrl()).into(viewHolder.ivImage);
        return view;

    }
    class ViewHolder{
        ImageView ivImage;
        TextView tvDesc;
        TextView tvShareTime;
    }
}
