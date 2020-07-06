package com.msalikhov.dictionary.utils

import kotlinx.coroutines.flow.flow

fun <T> wrapWithState(block: suspend () -> T) = flow {
    emit(State.Empty)
    try {
        emit(State.Success(block()))
    } catch (t: Throwable) {
        emit(State.Error(t))
    }
}

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(val data: Throwable) : State<Nothing>()
    object Empty : State<Nothing>()

    inline fun doOn(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onEmpty: () -> Unit = {}
    ) {
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(data)
            is Empty -> onEmpty()
        }
    }
}