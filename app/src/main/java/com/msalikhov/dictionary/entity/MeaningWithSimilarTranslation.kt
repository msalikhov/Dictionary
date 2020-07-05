package com.msalikhov.dictionary.entity

data class MeaningWithSimilarTranslation(
    val meaningId: Int,
    val frequencyPercent: String,
    val partOfSpeechAbbreviation: String,
    val translation: Translation
)