package com.antique.flow_mvvm.model

data class DailyUiState(
    val content: String,
    val created_at: String,
    val diary_id: Int,
    val emotion: String,
    val evaluation: Int
)