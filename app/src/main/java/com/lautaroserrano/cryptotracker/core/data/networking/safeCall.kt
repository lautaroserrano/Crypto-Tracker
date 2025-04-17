package com.lautaroserrano.cryptotracker.core.data.networking

import com.lautaroserrano.cryptotracker.core.domain.util.NetworkError
import com.lautaroserrano.cryptotracker.core.domain.util.Result
import kotlinx.coroutines.ensureActive
import retrofit2.Response
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext


suspend fun <T> safeCall(
    execute: suspend () -> Response<T>
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnknownHostException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }
    return responseToResult(response)
}
