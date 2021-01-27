package com.duo.medical.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.R;
import com.duo.medical.common.GlideRoundTransform;

import java.util.List;

public class IndexShopAdapter extends RecyclerView.Adapter<IndexShopAdapter.ViewHolder> implements View.OnClickListener {
    private List<ShopMode> data;
    private OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView;
    private Fragment fragment;

    public IndexShopAdapter(Fragment fragment,List<ShopMode> data){
        this.fragment=fragment;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index_shop, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round) //预加载图片
                .error(R.drawable.ic_launcher_foreground) //加载失败图片
                .priority(Priority.HIGH) //优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new GlideRoundTransform(10)); //圆角
        Glide.with(fragment).load(data.get(position).getImg_url()).apply(options).into(holder.ivShop);
        holder.tvPrice.setText(data.get(position).getPrice());
        holder.tvDesc.setText(data.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        int position=recyclerView.getChildAdapterPosition(v);
        if(null!=onItemClickListener){
            onItemClickListener.onItemClick(recyclerView,v,position,data.get(position));
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    /**
     *   将RecycleView附加到Adapter上
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView= recyclerView;
    }
    /**
     *   将RecycleView从Adapter解除
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener{
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent,View view, int position, ShopMode data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShop;
        TextView tvPrice;
        TextView tvDesc;
        public ViewHolder(View itemView) {
            super(itemView);
            ivShop = itemView.findViewById(R.id.img_shop);
            tvPrice = itemView.findViewById(R.id.price_shop);
            tvDesc =itemView.findViewById(R.id.desc_shop);
        }
    }
}
