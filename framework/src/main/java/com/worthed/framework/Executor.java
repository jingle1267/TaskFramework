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
import android.util.Log;

import com.worthed.framework.basic.Callback;

/**
 * 任务执行器，处理耗时操作和缓存
 * Created by jingle1267@163.com on 14-9-28.
 */
public abstract class Executor {

    private final boolean DEBUG = true;
    private final String TAG = getClass().getSimpleName();

    protected Request request;
    protected Task task;

    protected Executor(Task task, Request request) {
        this.task = task;
        this.request = request;
    }

    /**
     * 执行请求
     * @param context
     * @param callback
     */
    public void execute(Context context, Callback callback) {
        Response response;
        // 读取缓存数据
        if (task.isReadCache()) {
            response = readCache();
            response.setReadFromCache(true);
            callback.callback(response);
        }

        response = this.run(context, callback);

        // 保存缓存数据
        if (response != null) {
            response.setReadFromCache(false);
            if (task.isSaveCache()) {
                saveCache(response);
            }
        }
    }

    /**
     * 执行耗时操作
     * @param context
     * @param callback
     * @return
     */
    public abstract Response run(Context context, Callback callback);

    /**
     * 读取缓存
     * @return
     */
    public Response readCache() {
        if (DEBUG) {
            Log.d(TAG, "readCache()");
        }
        // 以下需要实现读取缓存
        return null;
    }

    /**
     * 写缓存
     * @param response
     */
    public void saveCache(Response response) {
        // 需要处理保存出错情况
        if (DEBUG) {
            Log.d(TAG, "saveCache()");
        }
    }

    public Task getTask() {
        return task;
    }

    public Request getRequest() {
        return request;
    }
}
