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

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.worthed.framework.Requestable;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class TaskService extends Service {

    public static final String FLAG_TASK_CLEAR = "clear_task_tag";
    private final boolean DEBUG = true;
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (DEBUG) {
            Log.d(TAG, "onStartCommand()");
        }
        if (intent == null) {
            return START_STICKY;
        }
        if (intent.getBooleanExtra(FLAG_TASK_CLEAR, false)) {
            clearTasks();
            return START_STICKY;
        }
        // if (intent.getExtras() == null) {
        //    return START_STICKY;
        // }
        if (intent.hasExtra(Requestable.FLAG_REQUST)) {
            Requestable request = intent.getParcelableExtra(Requestable.FLAG_REQUST);
            if (request != null) {
                Controller.instance(this).control(request);
            } else {
                if (DEBUG) {
                    Log.d(TAG, "request == null");
                }
            }
        } else {
            if (DEBUG) {
                Log.d(TAG, "do not have Requestable.FLAG_REQUEST");
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void clearTasks() {
        Controller.instance(this).clearTask();
    }
}
