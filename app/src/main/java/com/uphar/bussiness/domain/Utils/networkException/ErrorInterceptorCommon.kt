package com.agrireach.common.business.domain.utils.networkException

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ErrorInterceptorCommon(
    private val context: Context,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()

        val response = chain.proceed(request)

        // inspect status codes of unsuccessful responses
        when (response.code) {
            400 -> {

            }
        }

        return response
    }
}