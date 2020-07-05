package com.msalikhov.dictionary.domain

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

object PicassoProvider {
    fun build(context: Context, okHttpClient: OkHttpClient) = Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}