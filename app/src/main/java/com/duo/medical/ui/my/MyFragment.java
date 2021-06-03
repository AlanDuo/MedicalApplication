package com.duo.medical.ui.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duo.medical.R;
import com.duo.medical.common.GlideRoundTransform;
import com.duo.medical.common.http.NetClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyFragment extends Fragment {
    ImageView walletImg;
    ImageView prescriptionImg;
    ImageView archivesImg;
    ImageView feedbackImg;
    ImageView waitToPayImg;
    ImageView waitToReceiveImg;
    ImageView shopCartImg;
    ImageView orderImg;

    ImageView ivUserImg;
    TextView tvUsername;
    public static String userId;
    String phone;
    String idCard;
    public static String userImg;
    String username;
    String gender;

    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher_round) //预加载图片
                            .error(R.drawable.ic_launcher_foreground) //加载失败图片
                            .priority(Priority.HIGH) //优先级
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .transform(new GlideRoundTransform(50)); //圆角
                    Glide.with(MyFragment.this).load(userImg).apply(options).into(ivUserImg);
                    tvUsername.setText(username);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);

        ivUserImg=view.findViewById(R.id.iv_user_img);
        tvUsername=view.findViewById(R.id.tv_username);
        String userUrl="user/user/info";
        NetClient.getNetClient().callNet(userUrl, "GET", null, new NetClient.MyCallBack() {
            @Override
            public void onFailure(int code) {

            }

            @Override
            public void onResponse(String json) {
                Log.d("userInfo: ",json);
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    String data=jsonObject.getString("data");
                    JSONObject dataJson=new JSONObject(data);
                    userId=dataJson.getString("userId");
                    phone=dataJson.getString("phone");
                    idCard=dataJson.getString("idCard");
                    userImg=dataJson.getString("userImg");
                    username=dataJson.getString("username");
                    String genderByte=dataJson.getString("gender");
                    if("0".equals(genderByte)){
                        gender="男";
                    }else{
                        gender="女";
                    }

                }catch (Exception e){
                    Log.e("json转换异常",e.getMessage());
                }

                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        });
        ivUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PersonInfoActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("username",username);
                intent.putExtra("userImg",userImg);
                intent.putExtra("idCard",idCard);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
        walletImg=view.findViewById(R.id.iv_tools_wallet);
        walletImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),WalletActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        prescriptionImg=view.findViewById(R.id.iv_tools_my_prescription);
        prescriptionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PrescriptionActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        archivesImg=view.findViewById(R.id.iv_tools_healthy_archives);
        archivesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HealthyArchivesActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("username",username);
                intent.putExtra("userImg",userImg);
                intent.putExtra("gender",gender);
                startActivity(intent);
            }
        });

        feedbackImg=view.findViewById(R.id.iv_tools_feedback);
        feedbackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FeedBackActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        waitToPayImg=view.findViewById(R.id.iv_wait_to_pay);
        waitToPayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),WaitToPayActivity.class);
                intent.putExtra("userId",userId);
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
        ImageView ivCustomerService=view.findViewById(R.id.iv_customer_service);
        ivCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(getActivity())
                        .setMessage("拨打电话18178589543联系客服！")
                        .create();
                alertDialog.show();
            }
        });
        return view;
    }

}
