package com.lautaroserrano.cryptotracker.core.domain.util

enum class NetworkError : Error {
    SERIALIZATION,
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    NO_INTERNET,
    UNKNOWN
}
