package com.msalikhov.dictionary.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meaning(
    val id: String,
    val partOfSpeechCode: String,
    val translation: Translation,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String,
    val soundUrl: String
) : Parcelable {
    val partOfSpeech
        get() = when (partOfSpeechCode) {
            "v" -> "verb"
            "j" -> "adjective"
            "r" -> "adverb"
            "prp" -> "preposition"
            "prn" -> "pronoun"
            "crd" -> "cardinal number"
            "cjc" -> "conjunction"
            "exc" -> "interjection"
            "det" -> "article"
            "abb" -> "abbreviation"
            "x" -> "particle"
            "ord" -> "ordinal number"
            "md" -> "modal verb"
            "ph" -> "phrase"
            "phi" -> "idiom"
            "n" -> "noun"
            else -> ""
        }
}