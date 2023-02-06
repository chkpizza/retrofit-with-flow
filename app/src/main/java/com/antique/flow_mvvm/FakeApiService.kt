package com.antique.flow_mvvm

import com.antique.flow_mvvm.model.DailyResponse
import retrofit2.Response
import retrofit2.http.GET

interface FakeApiService {
    @GET("/v1/c3da6fe0-132d-4414-9dbe-e227078a7d18")
    suspend fun fetchDaily(): Response<DailyResponse>
}
