package com.antique.flow_mvvm.model

sealed class ApiStatus<out T> {
    object Loading : ApiStatus<Nothing>()
    data class Success<T>(val items: T) : ApiStatus<T>()
    data class Error(val errorMessage: String) : ApiStatus<Nothing>()
}