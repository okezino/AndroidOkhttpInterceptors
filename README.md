# AndroidOkhttpInterceptors

This is a very interesting and important aspect of Cleint Sever networking in android development,Interceptors are very good tool for debugging , offline 
caching and online catching , File manupulation and file compression, rewriting and retrying a network call on diferent scenario when Api is called.

In simple words, Interceptors are checkers or security personel that checks an individual before entring and exiting an even, Interceptors checks a network call 
before leaving the application and  before entering the application, giving developers room to run any event after checking. 

in this project , we will build custom interceptors and use already default interceptors , we will be using Okttp Library , Okttp Library host the Interceptor class and we will be using 
Retrofit for our network call service. 
```
 // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    //Okttp3
    implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2'
    implementation 'com.squareup.okhttp3:okhttp:5.0.0-alpha.2'
```
