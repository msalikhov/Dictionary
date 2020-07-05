package com.msalikhov.dictionary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.msalikhov.dictionary.R
import com.msalikhov.dictionary.entity.Meaning
import com.msalikhov.dictionary.entity.Word
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_word_description.*
import kotlinx.android.synthetic.main.item_meaning.view.*

class WordDescriptionFragment(private val picasso: Picasso) :
    Fragment(R.layout.fragment_word_description) {

    companion object {
        const val WORD_ARG = "arg_word"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val word = arguments?.getParcelable<Word>(WORD_ARG) ?: return
        text.text = word.text
        word.meanings.forEach {
            container.addMeaning(it)
        }
    }

    private fun LinearLayout.addMeaning(meaning: Meaning) {
        LayoutInflater
            .from(requireContext())
            .inflate(R.layout.item_meaning, this, false)
            .also {
                it.translation.text = getString(R.string.translation, meaning.translation.text)
                it.transcription.text = getString(R.string.transcription, meaning.transcription)
                it.partOfSpeech.text = getString(R.string.part_of_speech, meaning.partOfSpeech)
                picasso.load("https:${meaning.previewUrl}").into(it.image)
                addView(it)
            }
    }
}