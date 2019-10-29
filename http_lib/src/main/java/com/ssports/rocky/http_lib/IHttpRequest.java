package com.ssports.rocky.http_lib;

/**
 * Created by rocky on 2019/10/29.
 * 请求封装类
 */

public interface IHttpRequest {

    void setUrl(String url);

    void setRequestData(byte[] data);

    void setCallBack(IHttpCallBack callBack);

    void execute();

}
