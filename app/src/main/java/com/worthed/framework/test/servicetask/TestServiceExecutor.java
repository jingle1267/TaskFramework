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

package com.worthed.framework.test.servicetask;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.worthed.framework.ClientTaskManager;
import com.worthed.framework.Consumer;
import com.worthed.framework.Executor;
import com.worthed.framework.Request;
import com.worthed.framework.Response;
import com.worthed.framework.ServiceTaskManager;
import com.worthed.framework.Task;
import com.worthed.framework.TaskServiceManager;
import com.worthed.framework.basic.Callback;
import com.worthed.framework.test.task.TestRequest;

/**
 * Created by jingle1267@163.com on 14-10-1.
 */
public class TestServiceExecutor extends Executor implements Consumer{
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private Callback callback;

    public TestServiceExecutor(Task task, Request request) {
        super(task, request);
    }

    @Override
    public Response run(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
        Log.d(TAG, "run()");
        createTask("flag01");
        return null;
    }

    public void createTask(String taskFlag) {
        Log.d(TAG, "createTask() flag : " + taskFlag);
        Task task = new Task.Builder(taskFlag).setSync(false).setService(true).create();
        TestRequest request = new TestRequest(task);
        boolean isRegisterSuccess = ServiceTaskManager.instance().register(task, this);
        Log.d(TAG, "isRegisterSuccess : " + isRegisterSuccess);
        TaskServiceManager.send(context, task, request);
    }

    @Override
    public void consume(Response response) {
        String flag = response.getTask().getFlag();
        if ("flag01".equals(flag)) {

            createTask("flag02");
        } else if ("flag02".equals(flag)) {

            createTask("flag03");
        } else if ("flag03".equals(flag)) {
            TestServiceResponse testServiceResponse = new TestServiceResponse(this.request.getTask());
            callback.callback(testServiceResponse);
        }
        ServiceTaskManager.instance().unregister(response.getTask(), this);
    }
}
