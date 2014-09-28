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

import com.worthed.framework.Consumable;
import com.worthed.framework.Responsable;
import com.worthed.framework.Taskable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class TaskManagable {

    protected ConcurrentHashMap<Taskable, List<Consumable>> consumers;

    public TaskManagable() {
        consumers = new ConcurrentHashMap<Taskable, List<Consumable>>();
    }

    public boolean regist(Taskable task, Consumable consumer) {
        if (!consumers.containsKey(task)) {
            consumers.put(task, new Vector<Consumable>());
        }
        if (consumers.get(task).contains(consumer)) {
            return false;
        }
        consumers.get(task).add(consumer);
        return true;
    }

    public boolean unregist(Taskable task, Consumable consumer) {
        if (!consumers.containsKey(task)) {
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

    public void consume(Responsable response) {
        List<Consumable> consumables = consumers.get(response.task);
        if (consumables == null || consumables.size() < 1) {
            return;
        }
        List<Consumable> tmpConsumers = new ArrayList<Consumable>();
        for (Consumable consumer : consumables) {
            tmpConsumers.add(consumer);
        }
        for (Consumable consumer : tmpConsumers) {
            consumer.consume(response);
        }
    }

    public void removeConsumer(Consumable consumer) {
        Iterator<Taskable> it = consumers.keySet().iterator();
        Taskable task;
        List<Consumable> consumables;
        while (it.hasNext()) {
            task = it.next();
            consumables = consumers.get(task);
            if (consumables == null || consumables.isEmpty()) {
                consumers.remove(task);
                continue;
            }
            if (consumables.contains(consumer)) {
                consumables.remove(consumer);
            }
            if (consumables.isEmpty()) {
                consumables.remove(task);
            }
        }
    }

    public void clearConsumer() {
        if (consumers == null) {
            return;
        }
        Iterator<Taskable> it = consumers.keySet().iterator();
        Taskable task;
        List<Consumable> consumables;
        while (it.hasNext()) {
            task = it.next();
            consumables = consumers.get(task);
            if (consumables == null || consumables.isEmpty()) {
                consumers.remove(task);
                continue;
            }
            consumables.clear();
            if (consumables.isEmpty()) {
                consumers.remove(task);
            }
        }

    }
}
