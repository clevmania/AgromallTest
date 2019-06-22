package com.clevmania.agromalltest.data.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clevmania.agromalltest.data.model.ParamsModel
import com.clevmania.agromalltest.data.network.api.AgromallApiService
import com.clevmania.agromalltest.data.network.exception.NoConnectivityException
import com.clevmania.agromalltest.data.network.response.FarmerResponse
import com.clevmania.agromalltest.utils.UiUtils
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class AgromallDataSourceImpl(private val agromallApiService: AgromallApiService,
                             private val context : Context
) : AgromallDataSource {
    private val _retrieveFarmers = MutableLiveData<FarmerResponse>()
    override val retrieveFarmers: LiveData<FarmerResponse>
        get() = _retrieveFarmers

    override suspend fun fetchSampleFarmers(limit: String, page: String) {
        val params = ParamsModel(limit,page)
        try {
            val farmerRequest = agromallApiService.fetchFarmers(params).await()
            if(farmerRequest.isSuccessful)
                _retrieveFarmers.postValue(farmerRequest.body())
        }catch (e: NoConnectivityException){
            UiUtils.showToast(context, "Connectivity is turned off, please turn it on")
        }catch (ex: HttpException){
            UiUtils.showToast(context, ex.localizedMessage)
            Log.e("Farmers Fetch Failed",ex.toString())
        }catch (ex : ConnectException){
            UiUtils.showToast(context,"Cannot Access Host")
        }catch (ex: SocketTimeoutException){
            UiUtils.showToast(context, "Connection time out")
        }catch (ex: Exception){
            Log.e("Fetching Farmers Failed", ex.toString())
        }
    }
}