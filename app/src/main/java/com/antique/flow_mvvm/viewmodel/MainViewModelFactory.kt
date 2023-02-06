package com.antique.flow_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antique.flow_mvvm.FakeApiService
import com.antique.flow_mvvm.datasource.RemoteDataSource
import com.antique.flow_mvvm.repository.RepositoryImpl
import com.antique.flow_mvvm.usecase.GetDailyUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val fakeService = Retrofit.Builder()
                .baseUrl("https://mocki.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FakeApiService::class.java)


            return MainViewModel(GetDailyUseCase(RepositoryImpl(RemoteDataSource(fakeService)))) as T
        }
        throw IllegalArgumentException("unknown view model")
    }
}