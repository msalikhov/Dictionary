package com.msalikhov.dictionary.domain

import com.msalikhov.dictionary.entity.Word

class RepositoryImpl private constructor(private val networkApi: NetworkApi) : Repository {
    companion object {
        fun create(networkApi: NetworkApi): Repository = RepositoryImpl(networkApi)
    }

    override suspend fun searchWords(query: String): List<Word> = if (query.isNotBlank()) {
        networkApi.searchWords(query)
    } else {
        emptyList()
    }
}