package com.steve.moviestickearn.core.data

import android.util.Log
import com.steve.moviestickearn.core.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundSource2<RequestType> {

    private var result: Flow<Resource<RequestType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty("Kosong"))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }

    }


    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>


    fun asFlow(): Flow<Resource<RequestType>> = result
}