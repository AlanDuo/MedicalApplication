package com.duo.medical.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.duo.medical.R;

import java.util.List;

public class HealthyArchivesAdapter extends ArrayAdapter<HealthyArchivesListMode> {
    private List<HealthyArchivesListMode> mData;
    private Context mContext;
    private int resourceId;

    public HealthyArchivesAdapter(Context context,int resourceId,List<HealthyArchivesListMode> data){
        super(context,resourceId,data);
        this.mContext=context;
        this.resourceId=resourceId;
        this.mData=data;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        HealthyArchivesListMode archivesMode=getItem(position);

        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView sickName=view.findViewById(R.id.tv_archives_sick_name);
        TextView recordDate=view.findViewById(R.id.tv_archives_sick_time);
        sickName.setText(archivesMode.getSickName()+"（"+archivesMode.getPatient()+"）");
        recordDate.setText(archivesMode.getRecordDate());

        ViewHolder viewHolder;
        if(null==convertView){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.sickName=view.findViewById(R.id.tv_archives_sick_name);
            viewHolder.recordDate=view.findViewById(R.id.tv_archives_sick_time);

            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.sickName.setText(archivesMode.getSickName()+"（"+archivesMode.getPatient()+"）");
        viewHolder.recordDate.setText(archivesMode.getRecordDate());
        return view;
    }
    class ViewHolder {
        TextView sickName;
        TextView recordDate;
    }
}
