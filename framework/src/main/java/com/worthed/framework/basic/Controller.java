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
import android.os.Bundle;

import com.worthed.framework.Responsable;
import com.worthed.framework.ServiceTaskManager;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class Controller implements Callbackable, Statable {

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

    public void control(Bundle bundle) {

    }

    public void clearTask() {
        ServiceTaskManager.instance().consumers.clear();
    }

    @Override
    public void callback(Responsable response) {

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
