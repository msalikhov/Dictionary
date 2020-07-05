package com.msalikhov.dictionary.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.msalikhov.dictionary.R
import com.msalikhov.dictionary.entity.Word
import com.msalikhov.dictionary.utils.State
import com.msalikhov.dictionary.viewmodel.WordsSearchViewModel
import kotlinx.android.synthetic.main.fragment_words_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsSearchFragment : Fragment(R.layout.fragment_words_search) {
    private val wordsSearchViewModel: WordsSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchField.doAfterTextChanged {
            wordsSearchViewModel.searchWords(it?.toString().orEmpty())
        }

        wordsSearchViewModel.foundWords.observe(viewLifecycleOwner, this::onFoundWordsStateChanged)
    }

    private fun onFoundWordsStateChanged(state: State<List<Word>>) {
        when(state) {
            is State.Success -> TODO()
            is State.Error -> TODO()
            is State.Empty -> TODO()
        }
    }
}