package com.ssports.rocky.http_lib;

import java.io.InputStream;

/**
 * Created by rocky on 2019/10/29.
 * 内部封装回调
 */

public interface IHttpCallBack {


    void onSuccess(InputStream is);

    void onFailure();
}
