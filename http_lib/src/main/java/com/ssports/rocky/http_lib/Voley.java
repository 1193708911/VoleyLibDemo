package com.ssports.rocky.http_lib;


/**
 * Created by rocky on 2019/10/29.
 */

public class Voley {

    public static <T, M> void sendJsonRequest(T requestData, String url, Class<M> classType, CallBack<M> callBack) {
        IHttpRequest request = new jsonHttpReuqest();
        IHttpCallBack iHttpCallBack = new JsonHttpCallBack<>(classType, callBack);
        HttpTask<T> httpTask = new HttpTask<>(requestData, url, iHttpCallBack, request);
        ThreadExcuteManager.getInstance().excute(httpTask);
    }
}
