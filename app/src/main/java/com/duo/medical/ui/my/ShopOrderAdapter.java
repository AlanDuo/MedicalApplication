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

public class ShopOrderAdapter extends ArrayAdapter<ShopOrderListMode> {
    private List<ShopOrderListMode> mData;
    private Context mContext;
    private int resourceId;

    public ShopOrderAdapter(Context context,int resourceId,List<ShopOrderListMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.resourceId=resourceId;
        this.mData=data;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        ShopOrderListMode shopOrderMode=getItem(position);
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ImageView orderImg = view.findViewById(R.id.iv_shop_order_img);
        TextView orderDesc = view.findViewById(R.id.tv_shop_order_desc);
        TextView orderPrice = view.findViewById(R.id.tv_shop_order_price);

        Glide.with(view).load(shopOrderMode.getOrderImg()).into(orderImg);
        orderDesc.setText(shopOrderMode.getOrderDesc());
        orderPrice.setText(shopOrderMode.getOrderPrice());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.orderImg = view.findViewById(R.id.iv_shop_order_img);
            viewHolder.orderDesc = view.findViewById(R.id.tv_shop_order_desc);
            viewHolder.orderPrice = view.findViewById(R.id.tv_shop_order_price);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(shopOrderMode.getOrderImg()).into(viewHolder.orderImg);
        viewHolder.orderDesc.setText(shopOrderMode.getOrderDesc());
        viewHolder.orderPrice.setText(shopOrderMode.getOrderPrice());
        return view;

    }
    class ViewHolder{
        ImageView orderImg;
        TextView orderDesc;
        TextView orderPrice;
    }
}
