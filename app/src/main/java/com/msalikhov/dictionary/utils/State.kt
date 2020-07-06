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

val <T> T.exhaustive get() = this

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(val data: Throwable) : State<Nothing>()
    object Empty : State<Nothing>()
}