TaskFramework
=============

Android task framework. Less code, better quality. 

TaskFramework is an event bus designed to decouple different parts of your application
while still allowing them to communicate efficiently.


General Usage and API
---------------------

Using TaskFramework takes four simple steps:<br/>

1. Use <code>Task.Builder</code> to create a task holder;<br/>
2. Implement <code>Request.class</code>;<br/>
3. Extend <code>Response.class</code>;<br/>
4. Implement <code>Executor.class</code>;<br/>
5. Create a task:<br/>
```
String flag = "testAsync";
Task task = new Task.Builder(flag).setSync(false).create();
TestRequest request = new TestRequest(task);
boolean isRegisterSuccess = ClientTaskManager.instance().register(task, this);
TaskServiceManager.send(this, task, request);
```

Author
------
| [![HEAD](https://avatars2.githubusercontent.com/u/3887795?v=2&s=120)](http://worthed.com "Visit worthed.com") |
|---|
| [WORTHED.COM](http://worthed.com) |


License
-------

    Copyright (C) 2014 Zhenguo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 
[1]: http://worthed.com
