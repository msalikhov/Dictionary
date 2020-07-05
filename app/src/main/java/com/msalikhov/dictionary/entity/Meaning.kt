package com.msalikhov.dictionary.entity

data class Meaning(
    val id: String,
    val partOfSpeechCode: String,
    val translation: Translation,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String,
    val soundUrl: String
)