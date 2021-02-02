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

public class WaitToReceiveAdapter extends ArrayAdapter<ShopOrderListMode> {
    private List<ShopOrderListMode> mData;
    private Context mContext;
    private int resourceId;

    public WaitToReceiveAdapter(Context context, int resourceId, List<ShopOrderListMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.mData=data;
        this.resourceId=resourceId;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        ShopOrderListMode waitToReceiveMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView orderImg = view.findViewById(R.id.iv_wait_to_receive_img);
        TextView orderDesc = view.findViewById(R.id.tv_wait_to_receive_desc);
        TextView orderPrice = view.findViewById(R.id.tv_wait_to_receive_price);

        Glide.with(view).load(waitToReceiveMode.getOrderImg()).into(orderImg);
        orderDesc.setText(waitToReceiveMode.getOrderDesc());
        orderPrice.setText(waitToReceiveMode.getOrderPrice());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.orderImg = view.findViewById(R.id.iv_wait_to_receive_img);
            viewHolder.orderDesc = view.findViewById(R.id.tv_wait_to_receive_desc);
            viewHolder.orderPrice = view.findViewById(R.id.tv_wait_to_receive_price);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(waitToReceiveMode.getOrderImg()).into(viewHolder.orderImg);
        viewHolder.orderDesc.setText(waitToReceiveMode.getOrderDesc());
        viewHolder.orderPrice.setText(waitToReceiveMode.getOrderPrice());
        return view;
    }

    class ViewHolder{
        ImageView orderImg;
        TextView orderDesc;
        TextView orderPrice;
    }
}
