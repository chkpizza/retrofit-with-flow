package com.antique.flow_mvvm.usecase

import com.antique.flow_mvvm.model.DailyUiState
import com.antique.flow_mvvm.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetDailyUseCase(private val repository: Repository) {
    operator fun invoke() = repository.fetchDaily()
        .map { response ->
            response.toUiStateList()
        }
        .catch { e ->
            throw RuntimeException(e)
        }
}