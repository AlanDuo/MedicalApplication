package com.duo.medical.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.duo.medical.R;

public class MyFragment extends Fragment {
    ImageView walletImg;
    ImageView prescriptionImg;
    ImageView archivesImg;
    ImageView feedbackImg;
    ImageView waitToPayImg;
    ImageView waitToReceiveImg;
    ImageView shopCartImg;
    ImageView orderImg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);

        walletImg=view.findViewById(R.id.iv_tools_wallet);
        walletImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),WalletActivity.class);
                startActivity(intent);
            }
        });

        prescriptionImg=view.findViewById(R.id.iv_tools_my_prescription);
        prescriptionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PrescriptionActivity.class);
                startActivity(intent);
            }
        });

        archivesImg=view.findViewById(R.id.iv_tools_healthy_archives);
        archivesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HealthyArchivesActivity.class);
                startActivity(intent);
            }
        });

        feedbackImg=view.findViewById(R.id.iv_tools_feedback);
        feedbackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FeedBackActivity.class);
                startActivity(intent);
            }
        });

        waitToPayImg=view.findViewById(R.id.iv_wait_to_pay);
        waitToPayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),WaitToPayActivity.class);
                startActivity(intent);
            }
        });

        waitToReceiveImg=view.findViewById(R.id.iv_wait_to_receive);
        waitToReceiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),WaitToReceiveActivity.class);
                startActivity(intent);
            }
        });

        shopCartImg=view.findViewById(R.id.iv_shop_cart);
        shopCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ShopCartActivity.class);
                startActivity(intent);
            }
        });

        orderImg=view.findViewById(R.id.iv_my_order);
        orderImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),OrderActivity.class);

                startActivity(intent);
            }
        });
        return view;
    }

}
