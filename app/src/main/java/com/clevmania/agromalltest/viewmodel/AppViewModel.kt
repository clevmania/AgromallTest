package com.clevmania.agromalltest.viewmodel

import androidx.lifecycle.ViewModel
import com.clevmania.agromalltest.data.repository.AgromallRepository
import kotlinx.coroutines.*


class AppViewModel(private val agromallRepository: AgromallRepository) : ViewModel(){
    val allFarmers by lazyDeferred{agromallRepository.retrieveFarmers()}

    suspend fun retrieveAllFarmers(l: String, p: String) = agromallRepository.fetchSampleFarmers(l,p)

    private fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
        return lazy {
            GlobalScope.async(start = CoroutineStart.LAZY){
                block.invoke(this)
            }
        }
    }
}