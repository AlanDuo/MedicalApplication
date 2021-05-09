package com.duo.medical.common.http;

import android.util.Log;

import com.duo.medical.LoginActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 请求的封装
 */
public class NetClient {
    private static final String PATH="http://10.33.27.154:9201/";
    private static NetClient netClient;
    public final OkHttpClient client;
    private NetClient(){
        client=initOkHttpClient();
    }
    private OkHttpClient initOkHttpClient(){
        //初始化的时候自定义一些参数
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)//设置读取超时为10秒
                .connectTimeout(10000, TimeUnit.MILLISECONDS)//设置链接超时为10秒
                .build();
        return okHttpClient;
    }
    public static NetClient getNetClient(){
        if(null==netClient){
            netClient=new NetClient();
        }
        return netClient;
    }
    public void callNet(String url, String type, RequestBody body, final MyCallBack mCallback){
        Request request=null;
        url=PATH+url;
        if(type.equals("GET")){
            if(LoginActivity.token!=null) {
                request = new Request.Builder().url(url).addHeader("Authorization","Bearer "+ LoginActivity.token).build();
            }else{
                request = new Request.Builder().url(url).build();
            }
        }else if(type.equals("POST")){
            request=new Request.Builder().url(url).addHeader("Authorization","Bearer "+ LoginActivity.token).post(body).build();
        }else if(type.equals("PUT")){
            request=new Request.Builder().url(url).addHeader("Authorization","Bearer "+ LoginActivity.token).put(body).build();
        }else if (type.equals("DELETE")){
            request=new Request.Builder().url(url).addHeader("Authorization","Bearer "+ LoginActivity.token).delete(body).build();
        }

        Call call = getNetClient().initOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求网络失败，调用自己的接口，通过传回的-1可以知道错误类型
                //失败
                Log.e("TAG",Thread.currentThread().getName() + "结果  " + e.toString());
                mCallback.onFailure(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求网络成功说明服务器有响应，但返回的是什么我们无法确定，可以根据响应码判断
                if (response.code() == 200) {
                    //如果是200说明正常，调用MyCallBack的成功方法，传入数据
                    mCallback.onResponse(response.body().string());
                }else{
                    //如果不是200说明异常，调用MyCallBack的失败方法将响应码传入
                    mCallback.onFailure(response.code());
                }
            }
        });
    }
    public interface MyCallBack {
        //链接成功执行的方法
        void onFailure(int code);//方法参数用int数据类型，相当于是一个标识
        //链接失败执行的方法
        void onResponse(String json);//方法参数根据个人需求写，可以是字符串，也可以是输入流
    }
}
