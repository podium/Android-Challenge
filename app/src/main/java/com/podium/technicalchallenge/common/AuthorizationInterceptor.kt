package com.podium.technicalchallenge.common

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", "da2-7dph3xgybbffjn3mik4jii4equ")
            .build()

        return chain.proceed(request)
    }
}