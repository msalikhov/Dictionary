package com.msalikhov.dictionary.domain

import com.msalikhov.dictionary.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkProvider {
    private const val API_BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    private val logLevel get() = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    fun getOkHttp() = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        .build()

    fun getRetrofit(client: OkHttpClient) = Retrofit
        .Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getNetworkApi(retrofit: Retrofit) = retrofit.create<NetworkApi>()
}