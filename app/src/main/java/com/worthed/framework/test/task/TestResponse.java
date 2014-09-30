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

import android.os.Parcel;

import com.worthed.framework.Response;

/**
 * Created by jingle1267@163.com on 14-9-29.
 */
public class TestResponse extends Response {

    public static final Creator<TestResponse> CREATOR = new Creator<TestResponse>() {
        @Override
        public TestResponse createFromParcel(Parcel parcel) {
            return new TestResponse(parcel);
        }

        @Override
        public TestResponse[] newArray(int i) {
            return new TestResponse[i];
        }
    };

    public TestResponse() {

    }

    public TestResponse(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
