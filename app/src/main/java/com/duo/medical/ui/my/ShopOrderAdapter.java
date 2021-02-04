package com.duo.medical.ui.my;

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

import java.util.List;

public class ShopOrderAdapter extends ArrayAdapter<ShopOrderListMode> {
    private List<ShopOrderListMode> mData;
    private Context mContext;
    private int resourceId;
    private ViewHolder viewHolder;

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

        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.orderImg = view.findViewById(R.id.iv_shop_order_img);
            viewHolder.orderDesc = view.findViewById(R.id.tv_shop_order_desc);
            viewHolder.orderPrice = view.findViewById(R.id.tv_shop_order_price);
            viewHolder.orderDetail=view.findViewById(R.id.bt_shop_order_detail);
            viewHolder .orderEvaluate=view.findViewById(R.id.bt_shop_order_evaluate);
            viewHolder.orderAfterSales=view.findViewById(R.id.bt_shop_order_after_sales);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Glide.with(view).load(shopOrderMode.getOrderImg()).into(viewHolder.orderImg);
        viewHolder.orderDesc.setText(shopOrderMode.getOrderDesc());
        viewHolder.orderPrice.setText(shopOrderMode.getOrderPrice());
        viewHolder.orderDetail.setOnClickListener(new ButtonListener(position));
        viewHolder.orderEvaluate.setOnClickListener(new ButtonListener(position));
        viewHolder.orderAfterSales.setOnClickListener(new ButtonListener(position));

        return view;

    }
    class ViewHolder{
        ImageView orderImg;
        TextView orderDesc;
        TextView orderPrice;
        Button orderDetail;
        Button orderEvaluate;
        Button orderAfterSales;
    }
    class ButtonListener implements View.OnClickListener {
        private int position;

        public ButtonListener(int pos){
            position=pos;
        }
        @Override
        public void onClick(View v){
            int vId=v.getId();
            if(vId==viewHolder.orderDetail.getId()){
                Toast.makeText(mContext,"详情按钮",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,ShopOrderDetailActivity.class);
                mContext.startActivity(intent);
            } else if(vId==viewHolder.orderEvaluate.getId()){
                Toast.makeText(mContext,"评价按钮",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,ShopEvaluateActivity.class);
                mContext.startActivity(intent);
            }else if (vId==viewHolder.orderAfterSales.getId()){
                Toast.makeText(mContext,"售后按钮",Toast.LENGTH_SHORT).show();


            }
        }
    }
}
