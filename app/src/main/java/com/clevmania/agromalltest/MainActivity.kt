package com.clevmania.agromalltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clevmania.agromalltest.data.model.Farmer
import com.clevmania.agromalltest.data.model.SampleDisplayModel
import com.clevmania.agromalltest.data.network.AgromallDataSource
import com.clevmania.agromalltest.data.network.AgromallDataSourceImpl
import com.clevmania.agromalltest.data.network.api.AgromallApiService
import com.clevmania.agromalltest.data.network.interceptor.ConnectivityInterceptorImpl
import com.clevmania.agromalltest.data.network.response.FarmerResponse
import com.clevmania.agromalltest.data.repository.AgromallRepositoryImpl
import com.clevmania.agromalltest.utils.UiUtils
import com.clevmania.agromalltest.view.FarmerAdapter
import com.clevmania.agromalltest.viewmodel.AppViewModel
import com.clevmania.agromalltest.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), KodeinAware, CoroutineScope {
    override val kodein by kodein()
    private lateinit var viewModel : AppViewModel
    private lateinit var job: Job
//    private val appViewModelFactory : AppViewModelFactory
//    private val farmersList : MutableList<SampleDisplayModel> = mutableListOf()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showSampleFarmers()
    }

    private fun showSampleFarmers(){
        job = Job()
        val farmersList = arrayListOf<SampleDisplayModel>()
        pb_sample_farmers.visibility = View.VISIBLE
        val apiService = AgromallApiService(ConnectivityInterceptorImpl(this))
        val agromallDataSource = AgromallDataSourceImpl(apiService,this.applicationContext)

        agromallDataSource.retrieveFarmers.observe(this, Observer {
            if(it == null){
                pb_sample_farmers.visibility = View.GONE
                tv_status.text = getString(R.string.cannot_fetch)
                return@Observer
            }
            if(it.data.farmers.isNullOrEmpty()){
                tv_status.text = getString(R.string.no_farmers)
                pb_sample_farmers.visibility = View.GONE
                return@Observer
            }

            pb_sample_farmers.visibility = View.GONE
            tv_status.visibility = View.GONE

            it.data.farmers.forEach { farmer ->
                farmersList.add(SampleDisplayModel("${farmer.firstName} ${farmer.surname}",
                    farmer.passportPhoto,farmer.mobileNo,farmer.dob,"${farmer.city} \u2022 ${farmer.lga}"))
            }

            rv_farmers.setHasFixedSize(true)
            rv_farmers.layoutManager = LinearLayoutManager(this)
            rv_farmers.adapter = FarmerAdapter(farmersList)

        })

        GlobalScope.launch {
            agromallDataSource.fetchSampleFarmers("50","3")
        }

//        val apiService = AgromallApiService(ConnectivityInterceptorImpl(this@MainActivity))
//        val repository = AgromallRepositoryImpl(AgromallDataSourceImpl(apiService, applicationContext))
//        val appViewModelFactory = AppViewModelFactory(repository)

//        viewModel = ViewModelProviders.of(this, appViewModelFactory)
//            .get(AppViewModel::class.java)

    }

    private fun bindUiToViewModel() = launch {
        viewModel.retrieveAllFarmers("10","3")
        val agromallFarmers = viewModel.allFarmers.await()
        agromallFarmers.observe(this@MainActivity, Observer {
            if(it == null){
                return@Observer
            }
//            if(it.status != "true" || it.data.farmers.isNullOrEmpty())

//            pb_sample_farmers.visibility = View.GONE
            Log.i("FarmerInfo", it.toString())
            it.data.farmers.forEach {farmer->

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
