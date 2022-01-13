# Search App

The goal of the mobile technical challenge is to build a simple application to browse companies,
payroll platforms and gig platforms. 

### Running the app
Please make sure that you add './api.properties' file is setup correctly. 
```

apiKeyID=<YOUR_API_KEY_ID>
apiKeySecret=<YOUR_API_KEY_SECRET>

```

### Architecture
The project uses the [MVVM][mvvm] application architecture. 

[mvvm]: https://developer.android.com/jetpack/guide
### Libraries
* [Hilt][hilt] for dependency injection
* [Retrofit][retrofit] for making REST requests
* [Navigation][navigation]
* [Material][material]
* [Coroutines][coroutines] for executing code asynchronously
* [Coil][coil] for loading images
* [mockito][mockito] for mocking in tests

[hilt]: https://developer.android.com/training/dependency-injection/hilt-android
[retrofit]: https://github.com/square/retrofit
[navigation]: https://developer.android.com/guide/navigation
[material]: https://github.com/material-components/material-components-android
[coroutines]: https://developer.android.com/kotlin/coroutines
[coil]: https://github.com/coil-kt/coil
[mockito]: http://site.mockito.org

### Known bugs
There are no known bugs

License
--------
Copyright 2022 Marian Vasilca

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.