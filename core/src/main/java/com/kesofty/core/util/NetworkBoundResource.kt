package com.kesofty.core.util

import com.kesofty.core.extensions.loading
import kotlinx.coroutines.flow.*

//inline fun <ResultType, RequestType> networkBoundResource(
//    crossinline query: () -> Flow<ResultType>,
//    crossinline fetch: suspend () -> RequestType,
//    crossinline saveFetchResult: suspend (RequestType) -> Unit,
//    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
//    crossinline shouldFetch: (ResultType) -> Boolean = { true }
//) = flow<Result<ResultType>> {
//    emit(Result.loading(null))
//    val data = query().first()
//
//    val flow = if (shouldFetch(data)) {
//        emit(Resource.Loading(data))
//
//        try {
//            saveFetchResult(fetch())
//            query().map { Result.success(it) }
//        } catch (throwable: Throwable) {
//            onFetchFailed(throwable)
//            query().map { Result.success(throwable, it) }
//        }
//    } else {
//        query().map { Result.success(it) }
//    }
//
//    emitAll(flow)
//}