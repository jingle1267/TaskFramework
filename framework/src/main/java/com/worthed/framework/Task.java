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

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class Task implements Parcelable {

    // public final static String FLAG_TASK = "task_tag";

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel parcel) {
            return new Task(parcel);
        }

        @Override
        public Task[] newArray(int i) {
            return new Task[i];
        }
    };
    /**
     * 标识任务的类型
     */
    protected String flag;
    /**
     * 是否是单任务,如果是,在任务控制器里会判断该任务是否在任务队列里存在 如果存在则不执行
     */
    protected boolean singleTask = false;
    /**
     * 标识任务是否同步
     */
    protected boolean syncTask = true;
    /**
     * 标识任务是否是复合任务
     */
    protected boolean serviceTask = false;
    /**
     * 是否读取本地缓存数据
     */
    protected boolean readCache = false;
    /**
     * 是否保存数据到本地
     */
    protected boolean saveCache = false;

    public Task() {

    }

    public Task(Parcel source) {
        this.flag = source.readString();
        this.singleTask = source.readInt() == 0 ? false : true;
        this.syncTask = source.readInt() == 0 ? false : true;
        this.serviceTask = source.readInt() == 0 ? false : true;
        this.readCache = source.readInt() == 0 ? false : true;
        this.saveCache = source.readInt() == 0 ? false : true;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.flag);
        dest.writeInt(this.singleTask ? 1 : 0);
        dest.writeInt(this.syncTask ? 1 : 0);
        dest.writeInt(this.serviceTask ? 1 : 0);
        dest.writeInt(this.readCache ? 1 : 0);
        dest.writeInt(this.saveCache ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task task = (Task) o;
            if (!TextUtils.isEmpty(task.flag) && !TextUtils.isEmpty(flag)
                    && task.flag.equals(flag)) {
                return true;
            }
            if (TextUtils.isEmpty(task.flag) && TextUtils.isEmpty(flag)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (TextUtils.isEmpty(this.flag) ? "" : this.flag).hashCode();
    }

    public boolean isSingleTask() {
        return singleTask;
    }

    public void setSingleTask(boolean singleTask) {
        this.singleTask = singleTask;
    }

    public boolean isSyncTask() {
        return syncTask;
    }

    public void setSyncTask(boolean syncTask) {
        this.syncTask = syncTask;
    }

    public boolean isServiceTask() {
        return serviceTask;
    }

    public void setServiceTask(boolean serviceTask) {
        this.serviceTask = serviceTask;
    }

    public boolean isReadCache() {
        return readCache;
    }

    public void setReadCache(boolean readCache) {
        this.readCache = readCache;
    }

    public boolean isSaveCache() {
        return saveCache;
    }

    public void setSaveCache(boolean saveCache) {
        this.saveCache = saveCache;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}