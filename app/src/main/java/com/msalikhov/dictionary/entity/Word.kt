package com.msalikhov.dictionary.entity

data class Word(
    val id: Int,
    val text: String,
    val meaning: List<Meaning>
)