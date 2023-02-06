package com.antique.flow_mvvm.repository

import android.util.Log
import com.antique.flow_mvvm.datasource.RemoteDataSource
import com.antique.flow_mvvm.model.Daily
import com.antique.flow_mvvm.model.DailyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun fetchDaily(): Flow<DailyResponse> = flow {
        val response = remoteDataSource.fetchDaily()

        if(response.isSuccessful) {
            response.body()?.let {
                //Log.d("ApiServiceTest", it.toString())
                emit(it)
            }
            //throw RuntimeException("api service failure test")
        } else {
            throw RuntimeException("api service failure")
        }
    }
}









