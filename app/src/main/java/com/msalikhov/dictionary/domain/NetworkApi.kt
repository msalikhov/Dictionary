package com.msalikhov.dictionary.domain

import com.msalikhov.dictionary.entity.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("words/search")
    suspend fun searchWords(@Query("search") query: String): List<Word>
}