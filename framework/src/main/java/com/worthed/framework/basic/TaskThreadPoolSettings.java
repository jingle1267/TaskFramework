/*
 * Copyright 2014 zhenguo (jingle1267@163.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.worthed.framework.basic;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class TaskThreadPoolSettings {

    private static TaskThreadPoolSettings instance;
    private final String TAG = getClass().getSimpleName();
    public int corePoolSize = 5;
    public int maxPoolSize = 10;
    public long keepAliveTime = 20 * 1000;
    public LinkedBlockingQueue<Runnable> workQueue;
    public ThreadFactory threadFactory;

    private TaskThreadPoolSettings() {
        this.workQueue = new LinkedBlockingQueue<Runnable>(100);
        this.threadFactory = new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, TAG + " : " + count.getAndIncrement());
                thread.setPriority(Thread.NORM_PRIORITY - 1);
                return thread;
            }
        };
    }

    private TaskThreadPoolSettings(int corePoolSize, int maxPoolSize, long keepAliveTime) {
        this();
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    public static synchronized TaskThreadPoolSettings instance() {
        if (instance == null) {
            instance = new TaskThreadPoolSettings();
        }
        return instance;
    }

}
