TaskFramework
=============

Android task framework. **<font color="red">Less code, better quality!</font>**

TaskFramework is an event bus designed to decouple different parts of your application
while still allowing them to communicate efficiently.


General Usage and API
---------------------

Using TaskFramework takes four simple steps:<br/>

1. Use [<code>Task.Builder</code>][1] to create a task holder;<br/>
2. Implement [<code>Request.class</code>][2];<br/>
3. Extend [<code>Response.class</code>][3];<br/>
4. Implement [<code>Executor.class</code>][4];<br/>
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
| [WORTHED.COM][5] |

Contributing
------------

Please fork this repository and contribute back using
[pull requests][6].

Any contributions, large or small, major features, bug fixes, additional
language translations are welcomed and appreciated
but will be thoroughly reviewed and discussed.

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
 
[1]: https://github.com/jingle1267/TaskFramework/blob/master/framework/src/main/java/com/worthed/framework/Task.java
[2]: https://github.com/jingle1267/TaskFramework/blob/master/framework/src/main/java/com/worthed/framework/Request.java
[3]: https://github.com/jingle1267/TaskFramework/blob/master/framework/src/main/java/com/worthed/framework/Response.java
[4]: https://github.com/jingle1267/TaskFramework/blob/master/framework/src/main/java/com/worthed/framework/Executor.java
[5]: http://worthed.com
[6]: https://github.com/jingle1267/TaskFramework/pulls
