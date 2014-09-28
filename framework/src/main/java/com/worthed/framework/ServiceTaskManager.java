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

import com.worthed.framework.basic.TaskManagable;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class ServiceTaskManager {

    public static TaskManagable instance() {
        return new Singleton<TaskManagable>() {

            @Override
            protected TaskManagable create() {
                return new TaskManagable();
            }
        }.get();
    }

}
