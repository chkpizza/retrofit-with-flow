package com.antique.flow_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antique.flow_mvvm.model.ApiStatus
import com.antique.flow_mvvm.model.DailyUiState
import com.antique.flow_mvvm.usecase.GetDailyUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val getDailyUseCase: GetDailyUseCase) : ViewModel() {
    //private val _uiState = MutableLiveData<List<DailyUiState>>()
    //val uiState: LiveData<List<DailyUiState>> get() = _uiState

    private val _daily = MutableStateFlow<ApiStatus<List<DailyUiState>>>(ApiStatus.Loading)
    val daily: StateFlow<ApiStatus<List<DailyUiState>>> get() = _daily

    fun load() {
        viewModelScope.launch {
            getDailyUseCase().catch {
                _daily.value = ApiStatus.Error(it.message.toString())
            }.collect {
                _daily.value = ApiStatus.Success(it)
            }
        }
    }
}