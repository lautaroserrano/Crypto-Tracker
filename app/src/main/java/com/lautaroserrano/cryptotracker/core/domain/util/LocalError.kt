package com.lautaroserrano.cryptotracker.core.domain.util

enum class LocalError : Error {
    FILE_NOT_FOUND,
    SERIALIZATION,
    PERMISSION_DENIED,
    IO_ERROR,
    UNKNOWN
}