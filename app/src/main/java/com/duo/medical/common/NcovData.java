package com.duo.medical.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class NcovData {
    public static Map<String,Object> getNcovData() throws Exception {
        String allData=getAreaStat();
        Map<String,Object> map=new HashMap<>(6);
        int chineseConfirmedCount=0;
        int chineseIncrVoConfirmedIncr=0;
        int chineseCurrentConfirmedCount=0;
        int abroadConfirmedCount=0;
        int abroadIncrVoConfirmedIncr=0;
        int abroadCurrentConfirmedCount=0;
        JSONArray jsonArray=JSONArray.parseArray(allData);
        int size=jsonArray.size();
        for(int i=0;i<size;i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if("CHN".equals(jsonObject.getString("countryShortCode"))){
                chineseConfirmedCount=Integer.parseInt(jsonObject.getString("confirmedCount"));
                chineseCurrentConfirmedCount=Integer.parseInt(jsonObject.getString("currentConfirmedCount"));
                String incrVo=jsonObject.getString("incrVo");
                JSONObject incr=JSONObject.parseObject(incrVo);
                chineseIncrVoConfirmedIncr=Integer.parseInt(incr.getString("confirmedIncr"));
            }else{
                abroadConfirmedCount+= Integer.parseInt(jsonObject.getString("confirmedCount"));
                abroadCurrentConfirmedCount+=Integer.parseInt(jsonObject.getString("currentConfirmedCount"));
                String incrVo=jsonObject.getString("incrVo");
                JSONObject incr=JSONObject.parseObject(incrVo);
                abroadIncrVoConfirmedIncr+=Integer.parseInt(incr.getString("confirmedIncr"));
            }
        }
        map.put("chineseConfirmedCount",chineseConfirmedCount);
        map.put("chineseIncrVoConfirmedIncr",chineseIncrVoConfirmedIncr);
        map.put("chineseCurrentConfirmedCount",chineseCurrentConfirmedCount);
        map.put("abroadConfirmedCount",abroadConfirmedCount);
        map.put("abroadIncrVoConfirmedIncr",abroadIncrVoConfirmedIncr);
        map.put("abroadCurrentConfirmedCount",abroadCurrentConfirmedCount);
        return map;
    }
    /*public static void main(String[] args) throws Exception{
        getAreaStat();
    }*/


    private static String httpRequset(String requesturl) throws IOException {
        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpsURLConnection httpsURLConnection = null;
        try {
            URL url = new URL(requesturl);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setRequestMethod("GET");
            inputStream = httpsURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return buffer.toString();
    }


    public static String getAreaStat() throws Exception {
        String url = "https://ncov.dxy.cn/ncovh5/view/pneumonia";
        String htmlResult = "";
        try {
            htmlResult = httpRequset(url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //String reg = "window.getAreaStat = (.*?)\\}(?=catch)";
        String reg = "window.getListByCountryTypeService2true = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
        //System.out.println(htmlResult);
        String result = "";
        if (totalMatcher.find()) {
            result = totalMatcher.group(1);
        }
        return result;
    }
}
