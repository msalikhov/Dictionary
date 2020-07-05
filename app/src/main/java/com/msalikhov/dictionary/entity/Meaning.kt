package com.msalikhov.dictionary.entity

data class Meaning(
    val id: String,
    val wordId: Int,
    val difficultyLevel: Int,
    val partOfSpeechCode: String,
    val prefix: String,
    val text: String,
    val soundUrl: String,
    val transcription: String,
    val updatedAt: String,
    val mnemonics: String,
    val translation: Translation,
    val images: List<Image>,
    val definition: Definition,
    val examples: List<Example>,
    val meaningsWithSimilarTranslation: List<MeaningWithSimilarTranslation>,
    val alternativeTranslations: List<AlternativeTranslation>
)