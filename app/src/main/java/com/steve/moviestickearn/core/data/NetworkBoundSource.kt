package com.steve.moviestickearn.core.data

import android.util.Log
import com.steve.moviestickearn.core.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundSource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {

        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                saveCallResult(apiResponse.data)
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
            is ApiResponse.Empty -> {
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }

    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>


    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}