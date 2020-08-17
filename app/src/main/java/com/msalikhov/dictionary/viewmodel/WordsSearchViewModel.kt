package com.msalikhov.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import com.msalikhov.dictionary.Constants
import com.msalikhov.dictionary.domain.Repository
import com.msalikhov.dictionary.utils.wrapWithState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest

class WordsSearchViewModel(private val repository: Repository) : ViewModel() {

    private val queryChannel = MutableStateFlow("")
    val foundWords = this.queryChannel
        .debounce(Constants.NETWORK_DEBOUNCE)
        .distinctUntilChanged()
        .flatMapLatest {
            wrapWithState {
                repository.searchWords(it)
            }
        }

    fun searchWords(query: String) {
        this.queryChannel.value = query
    }
}