package com.clevmania.agromalltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clevmania.agromalltest.data.repository.AgromallRepository

class AppViewModelFactory(private val agromallRepository: AgromallRepository)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(agromallRepository) as T
    }
}