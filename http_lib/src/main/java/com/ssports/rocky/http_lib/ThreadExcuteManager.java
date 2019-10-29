package com.ssports.rocky.http_lib;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by rocky on 2019/10/29.
 */

public class ThreadExcuteManager {
    //单例创建对象

    private static ThreadExcuteManager threadExcuteManager = new ThreadExcuteManager();
    private static ThreadPoolExecutor threadPoolExecutor;
    private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public static ThreadExcuteManager getInstance() {
        return threadExcuteManager;
    }

    public ThreadExcuteManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        threadPoolExecutor.execute(runnable);
    }

    RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                if (r != null) {
                    queue.put(r);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    public void excute(Runnable rn) {
        try {
            if (rn != null) {
                queue.put(rn);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable take = null;
                try {
                    take = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (take != null) {
                    threadPoolExecutor.execute(take);
                }
            }

        }
    };

}
