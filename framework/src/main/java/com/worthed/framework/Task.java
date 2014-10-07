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
 * 任务标识 设置任务的属性，主要包括：标识（flag）、单例（single）、同步（sync）、复合（service）、读缓存（readCache）、写缓存（writeCache）。
 * Created by jingle1267@163.com on 14-9-28.
 */
public class Task implements Parcelable {

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
    protected boolean single = false;
    /**
     * 标识任务是否同步
     */
    protected boolean sync = true;
    /**
     * 标识任务是否是复合任务
     */
    protected boolean service = false;
    /**
     * 是否读取本地缓存数据
     */
    protected boolean readCache = false;
    /**
     * 是否保存数据到本地
     */
    protected boolean saveCache = false;

    public Task(String flag) {
        this.flag = flag;
    }

    public Task(Parcel source) {
        this.flag = source.readString();
        this.single = source.readInt() == 0 ? false : true;
        this.sync = source.readInt() == 0 ? false : true;
        this.service = source.readInt() == 0 ? false : true;
        this.readCache = source.readInt() == 0 ? false : true;
        this.saveCache = source.readInt() == 0 ? false : true;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.flag);
        dest.writeInt(this.single ? 1 : 0);
        dest.writeInt(this.sync ? 1 : 0);
        dest.writeInt(this.service ? 1 : 0);
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

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
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

    /**
     * Task创建者
     */
    public static class Builder{

        private Task task;

        public Builder(String flag) {
            task = new Task(flag);
        }

        /**
         * 设置是否同步
         * @param isSync
         * @return
         */
        public Builder setSync(boolean isSync) {
            task.setSync(isSync);
            return this;
        }

        /**
         * 设置是否单例任务
         * @param isSingle
         * @return
         */
        public Builder setSingle(boolean isSingle) {
            task.setSingle(isSingle);
            return this;
        }

        /**
         * 设置是否是复合任务
         * @param isService
         * @return
         */
        public Builder setService(boolean isService) {
            task.setService(isService);
            return this;
        }

        /**
         * 设置是否读缓存
         * @param isReadCache
         * @return
         */
        public Builder setReadCache(boolean isReadCache) {
            task.setReadCache(isReadCache);
            return this;
        }

        /**
         * 设置是否保存缓存
         * @param isSaveCache
         * @return
         */
        public Builder setSaveCache(boolean isSaveCache) {
            task.setSaveCache(isSaveCache);
            return this;
        }

        /**
         * 创建Task
         * @return
         */
        public Task create() {
            return task;
        }

    }

}
