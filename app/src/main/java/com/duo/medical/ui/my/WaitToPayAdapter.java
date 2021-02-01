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

public class WaitToPayAdapter extends ArrayAdapter<WaitToListMode> {
    private List<WaitToListMode> mData;
    private Context mContext;
    private int resourceId;

    public WaitToPayAdapter(Context context,int resourceId,List<WaitToListMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.mData=data;
        this.resourceId=resourceId;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        WaitToListMode waitToPayMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView orderImg = view.findViewById(R.id.iv_wait_to_pay_img);
        TextView orderDesc = view.findViewById(R.id.tv_wait_to_pay_desc);
        TextView orderPrice = view.findViewById(R.id.tv_wait_to_pay_price);

        Glide.with(view).load(waitToPayMode.getOrderImg()).into(orderImg);
        orderDesc.setText(waitToPayMode.getOrderDesc());
        orderPrice.setText(waitToPayMode.getOrderPrice());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.orderImg = view.findViewById(R.id.iv_wait_to_pay_img);
            viewHolder.orderDesc = view.findViewById(R.id.tv_wait_to_pay_desc);
            viewHolder.orderPrice = view.findViewById(R.id.tv_wait_to_pay_price);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(waitToPayMode.getOrderImg()).into(viewHolder.orderImg);
        viewHolder.orderDesc.setText(waitToPayMode.getOrderDesc());
        viewHolder.orderPrice.setText(waitToPayMode.getOrderPrice());
        return view;
    }

    class ViewHolder{
        ImageView orderImg;
        TextView orderDesc;
        TextView orderPrice;
    }
}
