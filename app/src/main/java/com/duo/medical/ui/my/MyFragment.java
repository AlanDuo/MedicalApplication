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
        return view;
    }

}
