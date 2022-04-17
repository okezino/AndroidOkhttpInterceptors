package com.example.test.interceptors

import com.example.test.isInternetAvailable
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This is use when you want the okktp to chat result even though, the vice is offline
 * This is an Application Interceptor
 */
class ForcedCacheInterceptors : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!isInternetAvailable())
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()

        return chain.proceed(request)

    }
}