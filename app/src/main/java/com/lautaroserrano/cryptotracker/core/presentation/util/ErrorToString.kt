package com.lautaroserrano.cryptotracker.core.presentation.util

import android.content.Context
import com.lautaroserrano.cryptotracker.R
import com.lautaroserrano.cryptotracker.core.domain.util.Error
import com.lautaroserrano.cryptotracker.core.domain.util.NetworkError

fun errorToString(error: Error, context: Context): String {
    val resId = when (error) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.SERVER_ERROR -> R.string.error_server_error
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        else -> R.string.error_unknown
    }
    return context.getString(resId)
}