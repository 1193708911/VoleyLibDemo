package com.ssports.rocky.http_lib;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rocky on 2019/10/29.
 * 真正的请求过程
 */

public class jsonHttpReuqest implements IHttpRequest {

    private String mUrl;
    private byte[] mData;
    private IHttpCallBack mCallBack;

    @Override
    public void setUrl(String url) {
        this.mUrl = url;
    }

    @Override
    public void setRequestData(byte[] data) {
        this.mData = data;
    }

    @Override
    public void setCallBack(IHttpCallBack callBack) {
        this.mCallBack = callBack;
    }

    //原生的网络操作在这里实现
    @Override
    public void execute() {
        httpUrlconnPost();
    }

    private HttpURLConnection urlConnection = null ;

    public void httpUrlconnPost(){
        URL url = null;
        try{
            url = new URL(this.mUrl);
            //打开http连接
            urlConnection = (HttpURLConnection) url.openConnection();
            //设置连接的超时时间
            urlConnection.setConnectTimeout(6000);
            //不使用缓存
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            //响应的超时时间
            urlConnection.setReadTimeout(3000);
            //设置这个连接是否可以写入数据
            urlConnection.setDoInput(true);
            //设置这个连接是否可以输出数据
            urlConnection.setDoOutput(true);
            //设置请求的方式
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.connect();

            //使用字节流发送数据
            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            if( mData != null ){
                //把字节数组的数据写入缓冲区
                bos.write(mData);
            }
            //刷新缓冲区，发送数据
            bos.flush();
            out.close();
            bos.close();

            //获得服务器响应
            if( urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = urlConnection.getInputStream();
                //回调监听器的listener方法
                mCallBack.onSuccess(in);
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

}
