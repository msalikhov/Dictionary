package com.msalikhov.dictionary.domain

import retrofit2.Retrofit

object ApiBuilder {
    fun build() = Retrofit
        .Builder()
        .baseUrl("")
        .addConverterFactory()
}