package task.framework.worthed.com.taskframework;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.worthed.framework.ClientTaskManager;
import com.worthed.framework.Consumable;
import com.worthed.framework.Responsable;
import com.worthed.framework.TaskServiceManager;
import com.worthed.framework.Taskable;

import task.framework.worthed.com.taskframework.task.TestRequest;


public class MyActivity extends Activity implements Consumable{

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
        test();
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

    public void test() {
        Log.d(TAG, "test()");
        Taskable taskable = new Taskable();
        taskable.setFlag("test");
        TestRequest request = new TestRequest();
        request.task = taskable;
        ClientTaskManager.instance().regist(taskable, this);
        TaskServiceManager.send(this, taskable, request);
    }

    @Override
    public void consume(Responsable response) {
        Log.d(TAG, "consume()");
    }
}
