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
var request = chain.request()

-----------------------------------------------------------------
  override fun intercept(chain: Interceptor.Chain): Response {
         var mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
         val newUser = UserInput("okeh.joseph@ymail", "$Github4")
         val requestBody : RequestBody = Request.create(mediaType, newUser.toString())
         val request : Request = chain.request()
             request = request.newBuilder()
                              .Post(requestBody)
                              .build()
             
         return chain.proceed(request)
    }

```

### Working on the Response 
 so lets learn how to modify or rewrite the response we are geting, we need to create a Response object to do so. 
 
 ```
 var response = chain.proceed(Request())
 -------------------------------------------------------
 
  override fun intercept(chain: Interceptor.Chain): Response {
       val mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
       val userResponse = UserResponse(token = "hasfhgfJGSVGSvzvgzhvcgvgczgcvzg")
       val responseBody = ResponseBody.create(mediaType, Gson.toJson(userResponse))
       val request = chain.request()
       
       
       
       var response = chain.proceed(requeest)
                       .newBuilder()
                       .code(401)
                       .body(responseBody)
                       
      return response.build()
    }
 
 ```
 
 ### UseCase of Custome Interceptor
 I will be using the 2 interceptor i created to show how to add an interceptor to a networkService in making network call, so i will be creating an instance of Retrofit, a client and my interceptors 
 
 #### Interceptors
 This takes a network call in the application, change the request Post Body and add a new Request body
 ```
 Class ApplicationInterceptor : Interceptor{
   override fun intercept(chain: Interceptor.Chain): Response {
         var mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
         val newUser = UserInput("okeh.joseph@ymail", "$Github4")
         val requestBody : RequestBody = Request.create(mediaType, newUser.toString())
         val request : Request = chain.request()
             request = request.newBuilder()
                              .Post(requestBody)
                              .build()
             
         return chain.proceed(request)
    }
 }
 ```
 
 This takes a network response, regardless of the response, change the code to 200 and change the reponse body to a token json file 
 
 ```
 Class NetworkInterceptor : Interceptor{
 
  override fun intercept(chain: Interceptor.Chain): Response {
       val mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
       val userResponse = UserResponse(token = "hasfhgfJGSVGSvzvgzhvcgvgczgcvzg")
       val responseBody = ResponseBody.create(mediaType, Gson.toJson(userResponse))
       val request = chain.request()
       
       
       
       var response = chain.proceed(requeest)
                       .newBuilder()
                       .code(401)
                       .body(responseBody)
                       
      return response.build()
    }
 
 }
 ```
 
 
 ### Okhttp Client 
 Now we have our interceptor, lets create an OkHTTP client 
 
 ```
 val modifyRequestData = ApplicationInterceptor()
 var modifyResponseData = NetworkInterceptor() 
 var okhttClient = OkhttpClient.newBuilder()
                   .addInterceptor(modifyRequestData)
                   .addNetworkInterceptor(modifyResponseData)
                   .build()
 ```
 ### Retrofit Client
 ```
 var retrofitInstance = Retrofit.Builder()
                        .baseUrl("http://www.example.com")
                        .client(okhttpClient)
                        .addConverterFactory(GsonConverterFactory.create()).build()
 
 var NetworkApiService = retrofit.create(NetworkService::Class.java)
                        
  ```
  
  Now you can use the NetworkApiService to make netwprk calls 
 
 
  
