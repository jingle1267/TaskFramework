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

package com.worthed.framework.test.task;

import android.content.Context;
import android.util.Log;

import com.worthed.framework.Executor;
import com.worthed.framework.Request;
import com.worthed.framework.Response;
import com.worthed.framework.Task;
import com.worthed.framework.basic.Callback;

/**
 * Created by jingle1267@163.com on 14-9-29.
 */
public class TestExecutor extends Executor {

    public TestExecutor(Task task, Request request) {
        super(task, request);
    }

    @Override
    public Response run(Context context, Callback callback) {
        Log.d("TestExecutor", "run()");
        TestResponse response = new TestResponse();
        response.setTask(task);
        callback.callback(response);
        return null;
    }
}
