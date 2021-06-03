package com.duo.medical.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.duo.medical.R;

import java.util.List;

public class BillAdapter extends ArrayAdapter<BillMode> {
    private List<BillMode> mData;
    private Context mContext;
    private int resourceId;

    public BillAdapter(Context context,int resourceId,List<BillMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.resourceId=resourceId;
        this.mData=data;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        BillMode billMode=getItem(position);

        View view;
        //view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tvBillType=view.findViewById(R.id.tv_bill_type);
            viewHolder.tvBillMoney=view.findViewById(R.id.tv_bill_money);
            viewHolder.tvBillDate=view.findViewById(R.id.tv_bill_date);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.tvBillType.setText(billMode.getIntOrOut());
        viewHolder.tvBillMoney.setText(billMode.getMoney());
        viewHolder.tvBillDate.setText(billMode.getBillTime());
        return view;
    }
    class ViewHolder {
        TextView tvBillType;
        TextView tvBillMoney;
        TextView tvBillDate;
    }
}
