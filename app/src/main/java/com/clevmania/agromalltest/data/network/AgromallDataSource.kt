package com.clevmania.agromalltest.data.network

import androidx.lifecycle.LiveData
import com.clevmania.agromalltest.data.network.response.FarmerResponse

interface AgromallDataSource {
    val retrieveFarmers : LiveData<FarmerResponse>

    suspend fun fetchSampleFarmers(limit: String, page: String)
}