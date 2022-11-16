package com.kesofty.core.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow<Result<ResultType>> {
    emit(Result.Loading)
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Result.Loading)

        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { Result.Error(throwable) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(flow)
}