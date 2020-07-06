package com.msalikhov.dictionary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.msalikhov.dictionary.domain.Repository
import com.msalikhov.dictionary.entity.Word
import com.msalikhov.dictionary.utils.State
import com.msalikhov.dictionary.viewmodel.WordsSearchViewModel
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WordSearchViewModelTest : Assert() {

    @Rule
    @JvmField
    val liveDataRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutinesRule = TestCoroutineRule()

    @Test
    fun testSuccess() {
        val repo = object : Repository {
            override suspend fun searchWords(query: String): List<Word> = emptyList()
        }
        val vm = WordsSearchViewModel(repo)
        coroutinesRule.testDispatcher.runBlockingTest {
            val flow = vm.foundWords.asFlow().take(2)
            vm.searchWords("")
            val expected = listOf(State.Empty, State.Success(emptyList<Word>()))
            assertEquals(expected, flow.toList())
        }
    }

    @Test
    fun testError() {
        val error = Throwable("test")
        val repo = object : Repository {
            override suspend fun searchWords(query: String): List<Word> {
                throw error
            }
        }
        val vm = WordsSearchViewModel(repo)
        coroutinesRule.testDispatcher.runBlockingTest {
            val flow = vm.foundWords.asFlow().take(2)
            vm.searchWords("")
            val expected = listOf(State.Empty, State.Error(error))
            assertEquals(expected, flow.toList())
        }
    }
}