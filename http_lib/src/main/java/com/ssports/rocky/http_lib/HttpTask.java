package com.ssports.rocky.http_lib;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by rocky on 2019/10/29.
 */

public class HttpTask<T> implements Runnable {


    private IHttpRequest iHttpRequest;
    private IHttpCallBack iHttpCallBack;

    public HttpTask(T requestData, String url, IHttpCallBack httpCallBack, IHttpRequest request) {
        try {
            this.iHttpCallBack = httpCallBack;
            this.iHttpRequest = request;
            request.setUrl(url);
            request.setRequestData(JSON.toJSONString(requestData).getBytes("UTF-8"));
            request.setCallBack(iHttpCallBack);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        iHttpRequest.execute();
    }
}
