package com.ssports.rocky.http_lib;

import android.os.Handler;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by rocky on 2019/10/29.
 * 内部封装请求处理类
 * 传入需要转换的实体对象
 * 传入回调接口
 */

public class JsonHttpCallBack<T> implements IHttpCallBack {

    private Class<T> mClassType;
    private CallBack<T> mCallBack;
    Handler UIHandler = new Handler();

    public JsonHttpCallBack(Class<T> classType, CallBack<T> callBack) {
        this.mCallBack = callBack;
        this.mClassType = classType;
    }

    @Override
    public void onSuccess(InputStream is) {

        String content = getContent(is);

        final T entity = JSON.parseObject(content, mClassType);

        UIHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mCallBack != null) {
                    mCallBack.onSuccess(entity);
                }
            }
        });


    }

    @Override
    public void onFailure() {

        UIHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mCallBack != null) {
                    mCallBack.onFailure();
                }
            }
        });

    }

    /**
     * 将流转换成字符串
     *
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        String content = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
