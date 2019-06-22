package com.clevmania.agromalltest.data.network.response

import com.clevmania.agromalltest.data.model.Data

data class FarmerResponse(
    val `data`: Data,
    val status: String
)