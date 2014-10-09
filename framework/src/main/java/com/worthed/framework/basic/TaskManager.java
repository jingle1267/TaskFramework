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

import android.util.Log;

import com.worthed.framework.Consumer;
import com.worthed.framework.Response;
import com.worthed.framework.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 任务管理工具类
 * Created by jingle1267@163.com on 14-9-28.
 */
public class TaskManager {

    private final boolean DEBUG = true;
    private final String TAG = getClass().getSimpleName();

    protected ConcurrentHashMap<Task, CopyOnWriteArrayList<Consumer>> consumers;

    public TaskManager() {
        consumers = new ConcurrentHashMap<Task, CopyOnWriteArrayList<Consumer>>();
    }

    /**
     * 注册任务
     *
     * @param task
     * @param consumer
     * @return
     */
    public boolean register(Task task, Consumer consumer) {
        if (!consumers.containsKey(task)) {
            consumers.put(task, new CopyOnWriteArrayList<Consumer>());
        } else {
            if (consumers.get(task).contains(consumer)) {
                if (DEBUG) {
                    Log.d(TAG, "consumers.get(task).contains(consumer)");
                }
                return false;
            }
        }
        consumers.get(task).add(consumer);
        if (DEBUG) {
            Log.d(TAG, "consumers.size() : " + consumers.size() + " - " + consumers.hashCode());
        }
        return true;
    }

    /**
     * 解除注册
     *
     * @param task
     * @param consumer
     * @return
     */
    public boolean unregister(Task task, Consumer consumer) {
        if (!consumers.containsKey(task)) {
            return false;
        }
        if (consumers.get(task) == null) {
            return false;
        }
        if (consumers.get(task).contains(consumer)) {
            consumers.get(task).remove(consumer);
        }
        if (consumers.get(task) == null || consumers.get(task).size() == 0) {
            consumers.remove(task);
        }
        return true;
    }

    /**
     * 消费任务
     *
     * @param response
     */
    public void consume(Response response) {
        if (DEBUG) {
            Log.d(TAG, "consume()");
            Log.d(TAG, "consumers.size() : " + this.consumers.size() + " - " + this.consumers.hashCode());
        }
        List<Consumer> consumers = this.consumers.get(response.getTask());
        if (consumers == null || consumers.size() < 1) {
            if (DEBUG) {
                if (consumers == null) {
                    Log.d(TAG, "consumables == null");
                } else {
                    Log.d(TAG, "consumables.size() < 1");
                }
            }
            return;
        }
        if (DEBUG) {
            Log.d(TAG, "consumables.size() : " + consumers.size());
        }
        List<Consumer> tmpConsumers = new ArrayList<Consumer>();
        for (Consumer consumer : consumers) {
            tmpConsumers.add(consumer);
        }
        for (Consumer consumer : tmpConsumers) {
            consumer.consume(response);
        }
    }

    /**
     * 取消消费者
     *
     * @param consumer
     */
    public void removeConsumer(Consumer consumer) {
        Iterator<Task> it = this.consumers.keySet().iterator();
        Task task;
        List<Consumer> consumers;
        while (it.hasNext()) {
            task = it.next();
            consumers = this.consumers.get(task);
            if (consumers == null || consumers.isEmpty()) {
                this.consumers.remove(task);
                continue;
            }
            if (consumers.contains(consumer)) {
                consumers.remove(consumer);
            }
            if (consumers.isEmpty()) {
                consumers.remove(task);
            }
        }
    }

    /**
     * 清楚所有的消费者
     */
    public void clearAllConsumers() {
        if (this.consumers == null) {
            return;
        } else {
            this.consumers.clear();
        }

//        Iterator<Task> it = this.consumers.keySet().iterator();
//        Task task;
//        List<Consumer> consumers;
//        while (it.hasNext()) {
//            task = it.next();
//            this.consumers.remove(task);
//
//            consumers = this.consumers.get(task);
//            if (consumers == null || consumers.isEmpty()) {
//                this.consumers.remove(task);
//                continue;
//            }
//            consumers.clear();
//            if (consumers.isEmpty()) {
//                this.consumers.remove(task);
//            }
//        }

    }
}
