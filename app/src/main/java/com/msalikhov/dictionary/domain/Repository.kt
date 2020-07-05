package com.msalikhov.dictionary.domain

import com.msalikhov.dictionary.entity.Word

interface Repository {
    suspend fun searchWords(query: String): List<Word>
}