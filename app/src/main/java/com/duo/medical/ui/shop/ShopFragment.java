package com.duo.medical.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.duo.medical.R;
import com.duo.medical.common.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    private HorizontalListView typeListView;
    private List<TypeMode> typeList;
    private RecyclerView recyclerView;
    private List<ShopMode> shopList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shop,container,false);

        typeListView=view.findViewById(R.id.horizontal_shop_type);
        typeModeListInit();
        MedicineTypeAdapter typeAdapter=new MedicineTypeAdapter(getContext(),R.layout.item_index_medicine_type,typeList);
        typeListView.setAdapter(typeAdapter);

        recyclerView=view.findViewById(R.id.shop_list);
        shopsInit();
        StaggeredGridLayoutManager staggeredGridLayoutManager=
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        IndexShopAdapter indexShopAdapter=new IndexShopAdapter(ShopFragment.this,shopList);
        indexShopAdapter.setOnItemClickListener(new IndexShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, ShopMode data) {
                Intent intent=new Intent(getActivity(),GoodsActivity.class);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(indexShopAdapter);

        return view;
    }

    public void typeModeListInit(){
        typeList=new ArrayList<>();
        typeList.add(new TypeMode(1,R.drawable.ic_internal_medicine,"内科"));
        typeList.add(new TypeMode(2,R.drawable.ic_surgery,"外科"));
        typeList.add(new TypeMode(3,R.drawable.ic_standby_drug,"常备"));
        typeList.add(new TypeMode(4,R.drawable.ic_chinese_medicine,"中药"));
        typeList.add(new TypeMode(5,R.drawable.ic_bisexual,"两性"));
        typeList.add(new TypeMode(6,R.drawable.ic_birth,"育儿"));
        typeList.add(new TypeMode(7,R.drawable.ic_head,"心脑"));
    }
    public void shopsInit(){
        shopList=new ArrayList<>();
        shopList.add(new ShopMode(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
        shopList.add(new ShopMode(2,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=550654640,1160439187&fm=26&gp=0.jpg","感冒解毒颗粒","¥ 20"));
        shopList.add(new ShopMode(3,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2839931069,3564949802&fm=26&gp=0.jpg","小儿感冒颗粒","¥ 20"));
        shopList.add(new ShopMode(4,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2652448203,3232878993&fm=26&gp=0.jpg","云南白药","¥ 20"));
        shopList.add(new ShopMode(5,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=327523080,341660430&fm=26&gp=0.jpg","保为康口罩","¥ 20"));
        shopList.add(new ShopMode(6,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=550654640,1160439187&fm=26&gp=0.jpg","感冒解毒颗粒","¥ 20"));
        shopList.add(new ShopMode(7,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2839931069,3564949802&fm=26&gp=0.jpg","小儿感冒颗粒","¥ 20"));
        shopList.add(new ShopMode(8,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2652448203,3232878993&fm=26&gp=0.jpg","云南白药","¥ 20"));
    }
}
