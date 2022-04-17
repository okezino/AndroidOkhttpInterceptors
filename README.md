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
 
 ## Creation of interceptors 
  We will create and interceptor, we will break each component of the interceptor and talk about it, to create an interceptor , create a classs and implement the interceptor Interface 
  ```
 class ModifyInputBody : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        /**
         * Our API Call will be intercepted here
         */
    }
}
```
We need to override the intercept function and in the block of the function, we can get access to the Request and even the Response at this point, after modifying the Request or the Response , we will return the new Response back to the network for transmitting. 

### Working on the request 
  in other to get the request, we will use chain.request()
  in other to modify the request we will use the newBuilder() on the request, modify the request and call the build() function to build it . 
  with the newbuilder() we can get access to a lot of Request  properties we can change . examples are addHeader, deleteHeader,Post, CacheControl etc .
  
  In modifing the request body, i will need to create a Requestbody Object and since its a Post request, i will add it to the request using the Post function and build the new request. 
  
```
  override fun intercept(chain: Interceptor.Chain): Response {
         var mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
         val newUser = UserInput("okeh.joseph@ymail", "$Github4")
         val requestBody : RequestBody = Request.create(mediaType, newUser.toString())
         val request : Request = chain.request()
             request = request.newBuilder().Post(requestBody).build()
             
         return chain.proceed(request)
    }

```
 
 
  
