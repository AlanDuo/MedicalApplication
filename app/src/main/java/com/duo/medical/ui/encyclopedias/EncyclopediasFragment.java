package com.duo.medical.ui.encyclopedias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.duo.medical.R;
import com.duo.medical.common.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediasFragment extends Fragment {
    private HorizontalListView horizontalListView;
    private List<EncyclopediasTypeMode> typeList;
    private ListView listView;
    private List<EncyclopediasMode> encyclopediasList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_encyclopedias,container,false);


        horizontalListView=view.findViewById(R.id.horizontal_news_type);
        encyclopediasTypeListInit();
        EncyclopediasTypeAdapter encyclopediasTypeAdapter=new EncyclopediasTypeAdapter(getContext(),R.layout.item_encyclopedias_type,typeList);
        horizontalListView.setAdapter(encyclopediasTypeAdapter);

        listView=view.findViewById(R.id.news_list);
        encyclopediasListInit();
        EncyclopediasListAdapter encyclopediasListAdapter=new EncyclopediasListAdapter(getContext(),R.layout.item_index_encyclopedias,encyclopediasList);
        listView.setAdapter(encyclopediasListAdapter);

        return view;
    }

    public void encyclopediasTypeListInit(){
        typeList=new ArrayList<>();
        typeList.add(new EncyclopediasTypeMode(1,R.drawable.ic_tcm_health,"中医养生"));
        typeList.add(new EncyclopediasTypeMode(2,R.drawable.ic_diet_plan,"饮食计划"));
        typeList.add(new EncyclopediasTypeMode(3,R.drawable.ic_heart_health,"心理健康"));
        typeList.add(new EncyclopediasTypeMode(4,R.drawable.ic_stomach_health,"肠胃呵护"));
        typeList.add(new EncyclopediasTypeMode(5,R.drawable.ic_cosmetology,"护肤美容"));
        typeList.add(new EncyclopediasTypeMode(6,R.drawable.ic_mouth_health,"口腔健康"));
    }
    public void encyclopediasListInit(){
        encyclopediasList=new ArrayList<>();
        encyclopediasList.add(new EncyclopediasMode("别让这几种坏习惯害了你","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.myquanwei.com%2Fsubject%2Fc%2F70%2F57%2Fe542a475q_1466653569635_640_551.jpg&refer=http%3A%2F%2Fimg.myquanwei.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219480&t=7fd4aea6fb5f18de9d2d9384208ded97","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("震惊！90%的中国人不知道这种东西的作用","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F10%2F04%2F1457f345dfae9d2.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219545&t=86875d4e84e9752fa468109819d19056","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("治疗脱发有这几样就够了","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F10%2F04%2F1457f345dfae9d2.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219545&t=86875d4e84e9752fa468109819d19056","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("别让这几种坏习惯害了你","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.myquanwei.com%2Fsubject%2Fc%2F70%2F57%2Fe542a475q_1466653569635_640_551.jpg&refer=http%3A%2F%2Fimg.myquanwei.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219480&t=7fd4aea6fb5f18de9d2d9384208ded97","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("震惊！90%的中国人不知道这种东西的作用","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F10%2F04%2F1457f345dfae9d2.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219545&t=86875d4e84e9752fa468109819d19056","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("治疗脱发有这几样就够了","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F10%2F04%2F1457f345dfae9d2.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219545&t=86875d4e84e9752fa468109819d19056","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("别让这几种坏习惯害了你","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.myquanwei.com%2Fsubject%2Fc%2F70%2F57%2Fe542a475q_1466653569635_640_551.jpg&refer=http%3A%2F%2Fimg.myquanwei.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219480&t=7fd4aea6fb5f18de9d2d9384208ded97","2021-1-26",1));
        encyclopediasList.add(new EncyclopediasMode("震惊！90%的中国人不知道这种东西的作用","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F10%2F04%2F1457f345dfae9d2.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614219545&t=86875d4e84e9752fa468109819d19056","2021-1-26",1));
    }
}
