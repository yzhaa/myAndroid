package com.yzh.myanroid.util;


import com.yzh.myanroid.db.MyCookieStorge;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class HttpURlConnectionUtill {

    public static InputStream getStreamResult(String Path){
        HttpURLConnection httpURLConnection ;
        try {
            URL url = new URL(Path);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5*1000);
            httpURLConnection.setConnectTimeout(5*1000);
            if(httpURLConnection.getResponseCode()==200){
                return httpURLConnection.getInputStream();
            }
            else {
                return httpURLConnection.getErrorStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static InputStream getStreamResultByPost(String path,String[] keys,String[] values){
        HttpURLConnection httpURLConnection ;
        try {
            Map<String, ?> map = MyCookieStorge.readCookie();
            URL url = new URL(path);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setReadTimeout(5*1000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(5*1000);
            if(map.size()!=0){
                httpURLConnection.setRequestProperty("Cookie","loginUserName="+map.get("name"));
                httpURLConnection.setRequestProperty("Cookie","loginUserPassword="+map.get("password"));
            }
            DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            for (; i < keys.length - 1; i++) {
                stringBuilder.append(keys[i]).append("=").append(values[i]).append("&");
            }
            stringBuilder.append(keys[i]).append("=").append(values[i]);
            out.writeBytes(stringBuilder.toString());
            out.flush();
            return httpURLConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getStringResultByPost(String path,String[] keys,String[] values){
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = getStreamResultByPost(path, keys,values);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while   (((line=bufferedReader.readLine())!=null)) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(bufferedReader!=null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String getStringResult(String path){
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = getStreamResult(path);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while   (((line=bufferedReader.readLine())!=null)) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                try {
                    if(bufferedReader!=null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return null;
    }

}

