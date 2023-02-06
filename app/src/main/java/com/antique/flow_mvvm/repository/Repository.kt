package com.antique.flow_mvvm.repository

import com.antique.flow_mvvm.model.Daily
import com.antique.flow_mvvm.model.DailyResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchDaily(): Flow<DailyResponse>
}


