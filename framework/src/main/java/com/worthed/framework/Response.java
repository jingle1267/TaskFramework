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

/**
 * Created by jingle1267@163.com on 14-9-28.
 */
public class Response implements Parcelable {
    public static final String FLAG_RESPONSE = "response_tag";
    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel parcel) {
            return new Response(parcel);
        }

        @Override
        public Response[] newArray(int i) {
            return new Response[i];
        }
    };
    protected Task task;
    protected String errorCode, errorMsg;

    public Response(Task task) {
        this.task = task;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Task getTask() {
        return task;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Response(Parcel source) {
        this.errorCode = source.readString();
        this.errorMsg = source.readString();
        this.task = source.readParcelable(Task.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.errorCode);
        parcel.writeString(this.errorMsg);
        parcel.writeParcelable(this.task, i);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
