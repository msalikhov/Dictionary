package com.msalikhov.dictionary.entity

import com.msalikhov.dictionary.utils.ListAdapterBuilder

data class Word(
    val id: Int,
    val text: String,
    val meaning: List<Meaning>
): ListAdapterBuilder.Diffable<Word> {
    override fun areItemsTheSame(item: Word) = id == item.id

    override fun areContentsTheSame(item: Word) = this == item
}