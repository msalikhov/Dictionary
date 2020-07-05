package com.msalikhov.dictionary.utils

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import com.msalikhov.dictionary.BuildConfig
import com.msalikhov.dictionary.R

fun Throwable.describeError(resources: Resources) =     if (BuildConfig.DEBUG) {
    localizedMessage ?: message ?: javaClass.simpleName
} else {
    resources.getString(R.string.default_error_description)
}

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_LONG) = Toast
    .makeText(this, msg, duration)
    .apply { show() }