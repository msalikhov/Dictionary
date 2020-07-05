package com.msalikhov.dictionary.entity

import android.os.Parcelable
import com.msalikhov.dictionary.utils.ListAdapterBuilder
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Word(
    val id: Int,
    val text: String,
    val meanings: List<Meaning>
) : ListAdapterBuilder.Diffable<Word>, Parcelable {
    override fun areItemsTheSame(item: Word) = id == item.id

    override fun areContentsTheSame(item: Word) = this == item
}