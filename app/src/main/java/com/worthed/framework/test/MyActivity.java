package com.worthed.framework.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.worthed.framework.ClientTaskManager;
import com.worthed.framework.Consumable;
import com.worthed.framework.Responsable;
import com.worthed.framework.TaskServiceManager;
import com.worthed.framework.Taskable;
import com.worthed.framework.test.task.TestRequest;

import task.framework.worthed.com.taskframework.R;


public class MyActivity extends Activity implements Consumable {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.w(TAG, "onCreate()");
        TaskServiceManager.start(this);

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
        Taskable taskable = new Taskable();
        taskable.setFlag("testSync");
        TestRequest request = new TestRequest(taskable);
        boolean isRegistSuccess = ClientTaskManager.instance().regist(taskable, this);
        Log.d(TAG, "isRegistSuccess : " + isRegistSuccess);
        TaskServiceManager.send(this, taskable, request);
    }

    public void testAsyncTask(View view) {
        Log.d(TAG, "testAsyncTask");
        Taskable task = new Taskable();
        task.setFlag("testAsync");
        task.setSyncTask(false);
        TestRequest request = new TestRequest(task);
        boolean isRegistSuccess = ClientTaskManager.instance().regist(task, this);
        Log.d(TAG, "isRegistSuccess : " + isRegistSuccess);
        TaskServiceManager.send(this, task, request);
    }

    @Override
    public void consume(Responsable response) {
        Log.d(TAG, "consume()");
        Taskable task = response.getTask();
        Log.d(TAG, "consume() flag : " + task.getFlag());
        ClientTaskManager.instance().unregist(task, this);
    }
}
