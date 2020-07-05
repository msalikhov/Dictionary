package com.msalikhov.dictionary.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias StateLiveData<T> = LiveData<State<T>>
typealias MutableStateLiveData<T> = MutableLiveData<State<T>>

fun <T> MutableStateLiveData<T>.success(value: T) {
    this.value = State.Success(value)
}

fun <T> MutableStateLiveData<T>.error(value: Throwable) {
    this.value = State.Error(value)
}

fun <T> MutableStateLiveData<T>.empty() {
    this.value = State.Empty
}

inline fun <T> wrapResult(action: ()->T): State<T> = try {
    State.Success(action())
} catch (t: Throwable) {
    State.Error(t)
}

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error(val data: Throwable) : State<Nothing>()
    object Empty : State<Nothing>()

    inline fun <R> map(mapper: (T) -> R): State<R> = switchMap { Success(mapper(it)) }

    inline fun <R> switchMap(mapper: (T) -> State<R>): State<R> = when (this) {
        is Success -> mapper(data)
        is Error -> this
        is Empty -> this
    }
}