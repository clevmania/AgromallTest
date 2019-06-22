package com.clevmania.agromalltest.data.network.api

import com.clevmania.agromalltest.data.model.ParamsModel
import com.clevmania.agromalltest.data.network.interceptor.ConnectivityInterceptor
import com.clevmania.agromalltest.data.network.response.FarmerResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

const val baseUrl = "https://theagromall.com/"

interface AgromallApiService {
    @POST("api/v2/get-sample-farmers")
    fun fetchFarmers(@Body paramsModel: ParamsModel
    ): Deferred<Response<FarmerResponse>>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): AgromallApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AgromallApiService::class.java)
        }
    }
}