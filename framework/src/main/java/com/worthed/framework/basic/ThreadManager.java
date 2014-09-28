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

import android.content.Context;

import com.worthed.framework.Executable;
import com.worthed.framework.Taskable;

/**
 * 管理任务线程
 * Created by jingle1267@163.com on 14-9-28.
 */
public class ThreadManager {

    private Context context;
    private Callbackable callbackable;
    private Statable statable;
    private TaskThreadPoolExecutor taskThreadPoolExecutor;

    public ThreadManager(Context context, Callbackable callbackable, Statable statable) {
        this.context = context;
        this.callbackable = callbackable;
        this.statable = statable;
        TaskThreadPoolSettings settings = TaskThreadPoolSettings.instance();
        taskThreadPoolExecutor = new TaskThreadPoolExecutor(settings);
    }

    public void addTask(final Executable executor) {
        if (executor == null) {
            return;
        }
        taskThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                statable.start();
                executor.run(context, callbackable);
                statable.end();
            }
        });
    }

    public void teminate() {
        taskThreadPoolExecutor.shutdown();
    }

}
