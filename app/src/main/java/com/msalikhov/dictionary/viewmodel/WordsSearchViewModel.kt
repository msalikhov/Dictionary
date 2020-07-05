package com.msalikhov.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.msalikhov.dictionary.Constants
import com.msalikhov.dictionary.domain.Repository
import com.msalikhov.dictionary.utils.stateFlow
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class WordsSearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val foundWords = queryChannel
        .asFlow()
        .debounce(Constants.NETWORK_DEBOUNCE)
        .distinctUntilChanged()
        .flatMapLatest { query->
            stateFlow {
                repository.searchWords(query)
            }
        }
        .asLiveData()

    fun searchWords(query: String) {
        viewModelScope.launch {
            queryChannel.send(query)
        }
    }
}