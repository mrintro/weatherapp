package com.example.weatherapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = {true}
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resources.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resources.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resources.Error(throwable, data) }
        }
    } else{
        query().map{ Resources.Success(it) }
    }

    emitAll(flow)

}