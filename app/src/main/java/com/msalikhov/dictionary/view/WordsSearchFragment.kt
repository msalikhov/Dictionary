package com.msalikhov.dictionary.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msalikhov.dictionary.R
import com.msalikhov.dictionary.entity.Word
import com.msalikhov.dictionary.utils.*
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
        when (state) {
            is State.Success -> {
                progressBar.isVisible = false
                wordsAdapter.submitList(state.data)
            }
            is State.Error -> {
                progressBar.isVisible = false
                wordsAdapter.submitList(emptyList())
                context?.showToast(state.data.describeError(resources))
            }
            is State.Empty -> {
                progressBar.isVisible = true
                wordsAdapter.submitList(emptyList())
            }
        }.exhaustive
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
                val word = wordsAdapter.currentList[adapterPosition]
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        WordDescriptionFragment::class.java,
                        bundleOf(WordDescriptionFragment.WORD_ARG to word)
                    )
                    .addToBackStack(WordDescriptionFragment::class.java.simpleName)
                    .commit()
            }
        }
}