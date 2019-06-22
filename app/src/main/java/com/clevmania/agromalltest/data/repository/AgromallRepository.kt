package com.clevmania.agromalltest.data.repository

import androidx.lifecycle.LiveData
import com.clevmania.agromalltest.data.network.response.FarmerResponse

interface AgromallRepository {
    suspend fun retrieveFarmers(): LiveData<FarmerResponse>

    suspend fun fetchSampleFarmers(limit: String, page: String)
}