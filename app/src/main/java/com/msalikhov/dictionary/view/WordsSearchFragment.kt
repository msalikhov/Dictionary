package com.msalikhov.dictionary.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msalikhov.dictionary.R
import com.msalikhov.dictionary.entity.Word
import com.msalikhov.dictionary.utils.ListAdapterBuilder
import com.msalikhov.dictionary.utils.State
import com.msalikhov.dictionary.utils.describeError
import com.msalikhov.dictionary.utils.showToast
import com.msalikhov.dictionary.viewmodel.WordsSearchViewModel
import kotlinx.android.synthetic.main.fragment_words_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsSearchFragment : Fragment(R.layout.fragment_words_search) {
    private val wordsSearchViewModel: WordsSearchViewModel by viewModel()
    private val wordsAdapter: ListAdapter<Word, *> =
        ListAdapterBuilder.build(R.layout.item_word, this::createWordViewHolder)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        resultsList.adapter = wordsAdapter
        searchField.doAfterTextChanged {
            wordsSearchViewModel.searchWords(it?.toString().orEmpty())
        }

        wordsSearchViewModel.foundWords.observe(viewLifecycleOwner, this::onFoundWordsStateChanged)
    }

    private fun onFoundWordsStateChanged(state: State<List<Word>>) {
        progressBar.isVisible = state is State.Empty
        state.doOn(
            onSuccess = { wordsAdapter.submitList(it) },
            onError = { context?.showToast(it.describeError(resources)) }
        )
    }

    private fun createWordViewHolder(view: View) =
        object : RecyclerView.ViewHolder(view), (Word) -> Unit, View.OnClickListener {
            private val text = itemView as TextView

            init {
                itemView.setOnClickListener(this)
            }

            override fun invoke(p1: Word) {
                text.text = p1.text
            }

            override fun onClick(v: View?) {

            }
        }
}