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

import android.os.Parcel;

import com.worthed.framework.Executor;
import com.worthed.framework.Request;
import com.worthed.framework.Task;

/**
 * Created by jingle1267@163.com on 14-10-1.
 */
public class TestServiceRequest extends Request {
    public TestServiceRequest(Task task) {
        super(task);
    }

    public TestServiceRequest(Parcel parcel) {
        super(parcel);
    }

    @Override
    protected Executor instanceExecutor() {
        return new TestServiceExecutor(task, this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TestServiceRequest> CREATOR = new Creator<TestServiceRequest>() {
        @Override
        public TestServiceRequest createFromParcel(Parcel parcel) {
            return new TestServiceRequest(parcel);
        }

        @Override
        public TestServiceRequest[] newArray(int i) {
            return new TestServiceRequest[i];
        }
    };
}
