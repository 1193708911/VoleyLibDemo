package com.ssports.rocky.http_lib;

/**
 * Created by rocky on 2019/10/29.
 * 外部封装回调
 */

public interface CallBack<T> {

    void onSuccess(T  t);

    void onFailure();
}
