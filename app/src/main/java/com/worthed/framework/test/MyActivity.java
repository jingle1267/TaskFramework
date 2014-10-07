package com.worthed.framework.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.worthed.framework.ClientTaskManager;
import com.worthed.framework.Consumer;
import com.worthed.framework.Response;
import com.worthed.framework.Task;
import com.worthed.framework.TaskServiceManager;
import com.worthed.framework.test.servicetask.TestServiceRequest;
import com.worthed.framework.test.task.TestRequest;

import task.framework.worthed.com.taskframework.R;


public class MyActivity extends Activity implements Consumer {

    private final String TAG = getClass().getSimpleName();

    private StringBuilder log;
    private TextView logTextView;
    private android.os.Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.w(TAG, "onCreate()");
        TaskServiceManager.start(this);
        logTextView = (TextView) findViewById(R.id.tv_log);
        log = new StringBuilder();
        log.append("Log : \n");
        handler = new android.os.Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void testSyncTask(View view) {
        Log.d(TAG, "testSyncTask()");
        String flag = "testSync";
        Task task = new Task.Builder(flag).create();
        TestRequest request = new TestRequest(task);
        boolean isRegisterSuccess = ClientTaskManager.instance().register(task, this);
        Log.d(TAG, "isRegisterSuccess : " + isRegisterSuccess);
        log.append("testSyncTask() flag - isRegisterSuccess : " + flag + " - " + isRegisterSuccess + "\n");
        showLog();
        TaskServiceManager.send(this, task, request);
    }

    public void testAsyncTask(View view) {
        Log.d(TAG, "testAsyncTask()");
        String flag = "testAsync";
        Task task = new Task.Builder(flag).setSync(false).create();
        TestRequest request = new TestRequest(task);
        boolean isRegisterSuccess = ClientTaskManager.instance().register(task, this);
        Log.d(TAG, "isRegisterSuccess : " + isRegisterSuccess);
        TaskServiceManager.send(this, task, request);
        log.append("testSyncTask() flag - isRegisterSuccess : " + flag + " - " + isRegisterSuccess + "\n");
        showLog();
    }

    public void testServiceTask(View view) {
        Log.d(TAG, "testServiceTask()");
        String flag = "serviceTag";
        Task task = new Task.Builder(flag).setSync(false).create();
        TestServiceRequest request = new TestServiceRequest(task);
        boolean isRegisterSuccess = ClientTaskManager.instance().register(task, this);
        Log.d(TAG, "isRegisterSuccess : " + isRegisterSuccess);
        TaskServiceManager.send(this, task, request);
        log.append("testSyncTask() flag - isRegisterSuccess : " + flag + " - " + isRegisterSuccess
                + "\n");
        showLog();
    }

    public void clearTasks(View view) {
        Log.d(TAG, "testServiceTask()");
        TaskServiceManager.clearTasks(this);
        // log.append("testSyncTask()"  + "\n");
        log.setLength(0);
        log.append("Log :\n");
        showLog();
    }

    @Override
    public void consume(Response response) {
        Log.d(TAG, "consume()");
        Task task = response.getTask();
        Log.d(TAG, "consume() flag - threadName: " + task.getFlag() + " - " + Thread.currentThread().getName());
        log.append("consume() flag : " + task.getFlag() + "\n");
        ClientTaskManager.instance().unregister(task, this);
        showLog();
    }

    private void showLog() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                logTextView.setText(log.toString());
            }
        });
    }

}
