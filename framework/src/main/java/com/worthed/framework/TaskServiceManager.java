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

package com.worthed.framework;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.worthed.framework.basic.TaskService;

/**
 * TaskService管理类
 * Created by jingle1267@163.com on 14-9-28.
 */
public class TaskServiceManager {

    private static final boolean DEBUG = true;
    private static final String TAG = "TaskServiceManager";

    /**
     * 启动服务
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, TaskService.class);
        context.startService(intent);
    }

    /**
     * 关闭服务
     *
     * @param context
     */
    public static void stop(Context context) {
        Intent intent = new Intent(context, TaskService.class);
        context.stopService(intent);
    }

    /**
     * 发送任务
     */
    public static void send(Context context, Task task, Request request) {
        if (DEBUG) {
            Log.d(TAG, "send()");
        }
        if (task == null || request == null) {
            if (DEBUG) {
                Log.e(TAG, "send()  task == null || request == null");
            }
            return;
        }
        Intent intent = new Intent(context, TaskService.class);
        request.task = task;
        intent.putExtra(Request.FLAG_REQUST, request);
        context.startService(intent);
    }

    /**
     * 清空任务
     * @param context
     */
    public static void clearTasks(Context context) {
        Intent intent = new Intent(context, TaskService.class);
        intent.putExtra(TaskService.FLAG_TASK_CLEAR, true);
        context.startService(intent);
    }

}
