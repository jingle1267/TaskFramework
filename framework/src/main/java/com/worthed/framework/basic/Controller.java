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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.worthed.framework.ClientTaskManager;
import com.worthed.framework.Requestable;
import com.worthed.framework.Responsable;
import com.worthed.framework.ServiceTaskManager;
import com.worthed.framework.Taskable;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class Controller implements Callbackable, Statable {

    private final boolean DEBUG = true;
    private final String TAG = getClass().getSimpleName();

    private static Context context;
    private static Controller instance;

    private ThreadManager threadManager;

    private Controller(Context context) {
        Controller.context = context;
        threadManager = new ThreadManager(context, this, this);
    }

    public static Controller instance(Context context) {
        if (instance == null) {
            instance = new Controller(context);
        } else {
            Controller.context = context;
        }
        return instance;
    }

    public void control(Requestable request) {
        if (DEBUG) {
            Log.d(TAG, "control()");
        }
        Taskable task = request.task;
        if (task.isSingleTask()) {
            if (ClientTaskManager.instance().consumers.contains(task)
                    || ServiceTaskManager.instance().consumers.contains(task)) {
                return;
            }
        }
        //request.settesk
        if (task.isSyncTask()) {
            request.getExecutable().run(context, this);
            return;
        }
        threadManager.addTask(request.getExecutable());
    }

    public void clearTask() {
        if (DEBUG) {
            Log.d(TAG, "clearTask()");
        }
        ClientTaskManager.instance().consumers.clear();
        ServiceTaskManager.instance().consumers.clear();
    }

    @Override
    public void callback(Responsable response) {
        if (DEBUG) {
            Log.d(TAG, "callback()");
        }
        if (response.task.isServiceTask()) {
            ServiceTaskManager.instance().consume(response);
        } else {
            ClientTaskManager.instance().consume(response);
//            Intent intent = new Intent();
//            intent.putExtra(Requestable.FLAG_REQUST, response);
//            context.sendBroadcast(intent);
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void end() {

    }

    @Override
    public void clear() {

    }
}
