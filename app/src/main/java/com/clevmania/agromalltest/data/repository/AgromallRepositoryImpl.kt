package com.clevmania.agromalltest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clevmania.agromalltest.data.network.AgromallDataSource
import com.clevmania.agromalltest.data.network.response.FarmerResponse

class AgromallRepositoryImpl(private val agromallDataSource: AgromallDataSource) : AgromallRepository {
    private var sampleFarmers = MutableLiveData<FarmerResponse>()
    override suspend fun retrieveFarmers(): LiveData<FarmerResponse> {
        return sampleFarmers
    }

    override suspend fun fetchSampleFarmers(limit: String, page: String) {
        agromallDataSource.fetchSampleFarmers(limit,page)
    }

    init {
        agromallDataSource.retrieveFarmers.observeForever {
            sampleFarmers.value = it
        }
    }
}