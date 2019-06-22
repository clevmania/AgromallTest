package com.clevmania.agromalltest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clevmania.agromalltest.R
import com.clevmania.agromalltest.data.model.SampleDisplayModel
import com.clevmania.agromalltest.utils.GlideApp
import kotlinx.android.synthetic.main.item_farmer.view.*

class FarmerAdapter(private val farmersList: List<SampleDisplayModel>)
    : RecyclerView.Adapter<FarmerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farmer,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()= farmersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(farmersList[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val baseImgUrl = "https://s3-eu-west-1.amazonaws.com/agromall-storage/"

        fun bindView(farmers: SampleDisplayModel) {
            itemView.tv_farmer_name.text = farmers.name
            itemView.tv_farmer_mobile.text = farmers.mobile
            itemView.tv_locale.text = farmers.area
            itemView.tv_dob.text = farmers.birthday
            GlideApp.with(itemView.context).load("$baseImgUrl${farmers.img}").into(itemView.iv_farmer_img)
        }
    }
}