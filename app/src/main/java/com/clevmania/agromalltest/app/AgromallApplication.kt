package com.clevmania.agromalltest.app

import android.app.Application
import com.clevmania.agromalltest.data.network.AgromallDataSource
import com.clevmania.agromalltest.data.network.AgromallDataSourceImpl
import com.clevmania.agromalltest.data.network.api.AgromallApiService
import com.clevmania.agromalltest.data.network.interceptor.ConnectivityInterceptor
import com.clevmania.agromalltest.data.network.interceptor.ConnectivityInterceptorImpl
import com.clevmania.agromalltest.data.repository.AgromallRepository
import com.clevmania.agromalltest.data.repository.AgromallRepositoryImpl
import com.clevmania.agromalltest.viewmodel.AppViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AgromallApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AgromallApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { AgromallApiService(instance()) }

        bind<AgromallDataSource>() with singleton { AgromallDataSourceImpl(instance(),instance()) }
        bind<AgromallRepository>() with singleton { AgromallRepositoryImpl(instance()) }
        bind() from provider { AppViewModelFactory(instance()) }
    }
}