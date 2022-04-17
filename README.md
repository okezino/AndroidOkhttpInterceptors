# AndroidOkhttpInterceptors

This is a very interesting and important aspect of Cleint Sever networking in android development,Interceptors are very good tool for debugging , offline 
caching and online catching , File manupulation and file compression, rewriting and retrying a network call on diferent scenario when Api is called.

In simple words, Interceptors are checkers or security personel that checks an individual before entring and exiting an even, Interceptors checks a network call 
before leaving the application and  before entering the application, giving developers room to run any event after checking. 

in this project , we will build custom interceptors and use already default interceptors , we will be using Okttp Library , Okttp Library host the Interceptor class and we will be using 
Retrofit for our network call service. 
```
    Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    Okttp3
    implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2'
    implementation 'com.squareup.okhttp3:okhttp:5.0.0-alpha.2'
```

## Types Of Interceptor
 There are 2 types of interceptors : 
   the Okhttp Library stands like a bridge between the application and the network server 
   
   <img width="717" alt="Screenshot 2022-04-17 at 16 53 09" src="https://user-images.githubusercontent.com/46386915/163722820-342627e2-c7a2-4c35-9275-6f8445e37acb.png">

 
 ### Application Interceptor
 These are interceptor that makes modification or are run before the network call leave the application to the Okhttp core or entering the application from the Okhttp core, there called using the 
 addinterceptor() before adding them to an okhttp client 
 
 ### Network Interceptor 
 These are interceptor that makes modification or are run before the network call coming from server  enters the Okhttp Core or exiting the Okhttp core to server and then into the application, there are called using the addNetworkInterceptor()
 
 
  
