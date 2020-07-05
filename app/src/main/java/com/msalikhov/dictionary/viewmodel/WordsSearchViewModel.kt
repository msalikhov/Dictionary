package com.msalikhov.dictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msalikhov.dictionary.domain.Repository
import com.msalikhov.dictionary.entity.Word
import com.msalikhov.dictionary.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WordsSearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _foundWords: MutableStateLiveData<List<Word>> = MutableLiveData()
    val foundWords: StateLiveData<List<Word>> get() = _foundWords

    fun searchWords(query: String) {
        //todo distinct
        //todo debounce
        //todo paging
        //todo caching?
        viewModelScope.launch {
            _foundWords.wrap {
                repository.searchWords(query)
            }
        }
    }
}