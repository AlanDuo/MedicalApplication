package com.duo.medical.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.R;
import com.duo.medical.common.GlideRoundTransform;

import java.util.List;

public class ShopCartAdapter extends ArrayAdapter<ShopCartMode> implements View.OnClickListener {
    private List<ShopCartMode> mData;
    private Context mContext;
    private int resourceId;
    private ViewHolder viewHolder;

    public ShopCartAdapter(Context context,int resourceId,List<ShopCartMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.mData=data;
        this.resourceId=resourceId;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        ShopCartMode shopCartMode=getItem(position);
        View view;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.shopImg=view.findViewById(R.id.iv_shop_cart_shop_img);
            viewHolder.shopDesc=view.findViewById(R.id.tv_shop_cart_shop_desc);
            viewHolder.shopType=view.findViewById(R.id.tv_shop_cart_shop_type);
            viewHolder.shopPrice=view.findViewById(R.id.tv_shop_cart_shop_price);
            viewHolder.shopAmount=view.findViewById(R.id.tv_shop_cart_shop_amount);
            viewHolder.chooseIt=view.findViewById(R.id.iv_choose_it);
            viewHolder.add=view.findViewById(R.id.bt_shop_cart_amount_add);
            viewHolder.decrease=view.findViewById(R.id.bt_shop_cart_amount_decrease);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round) //预加载图片
                .error(R.drawable.ic_launcher_foreground) //加载失败图片
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new GlideRoundTransform(10)); //圆角

        Glide.with(view).load(shopCartMode.getShopImg()).apply(options).into(viewHolder.shopImg);
        viewHolder.shopDesc.setText(shopCartMode.getShopDesc());
        viewHolder.shopType.setText(shopCartMode.getShopType());
        viewHolder.shopPrice.setText("¥ "+shopCartMode.getShopPrice());
        viewHolder.shopAmount.setText(shopCartMode.getShopAmount()+"");
        viewHolder.chooseIt.setOnClickListener(this);
        viewHolder.decrease.setOnClickListener(this);
        viewHolder.add.setOnClickListener(this);

        viewHolder.chooseIt.setTag(position);
        viewHolder.decrease.setTag(position);
        viewHolder.add.setTag(position);
        return view;
    }
    @Override
    public void onClick(View v){
        int vId=v.getId();
        if(vId==viewHolder.chooseIt.getId()){
            viewHolder.chooseIt.setImageResource(R.drawable.ic_circle_right);
        }else if(vId==viewHolder.decrease.getId()){
            viewHolder.shopAmount.setText((Integer.parseInt(viewHolder.shopAmount.getText()+"")-1)+"");
        }else if(vId==viewHolder.add.getId()){
            viewHolder.shopAmount.setText((Integer.parseInt(viewHolder.shopAmount.getText()+"")+1)+"");
        }
    }
    class ViewHolder{
        ImageView chooseIt;
        ImageView shopImg;
        TextView shopDesc;
        TextView shopType;
        TextView shopPrice;
        TextView shopAmount;
        Button decrease;
        Button add;
    }
}
