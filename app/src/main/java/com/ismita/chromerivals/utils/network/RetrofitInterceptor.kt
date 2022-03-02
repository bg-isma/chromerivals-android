package com.ismita.chromerivals.utils.network

import com.ismita.chromerivals.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Accept", "*/*")
                .addHeader("Cr-Api-Key", BuildConfig.CR_PUBLIC_API_KEY)
                .build()
        )
    }
}