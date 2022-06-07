package com.jeanca.gitapp.api

import com.google.gson.GsonBuilder
import com.jeanca.gitapp.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Provider {

    companion object {

        private var converterFactory: GsonConverterFactory? = null

        fun getHttpClient(token: String): OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor {
                val builder = it.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer $token"
                    )
                it.proceed(builder.build())
            }.build()

        val converter: GsonConverterFactory
            get() {
                if (converterFactory == null) {
                    converterFactory = GsonConverterFactory
                        .create(GsonBuilder().setLenient().disableHtmlEscaping().create())
                }
                return converterFactory!!
            }

    }
}

object ApiProvider {
    fun provider(token: String): APIService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(Provider.getHttpClient(token))
        .addConverterFactory(Provider.converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(APIService::class.java)
}