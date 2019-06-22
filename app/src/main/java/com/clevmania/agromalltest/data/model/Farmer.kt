package com.clevmania.agromalltest.data.model

import com.google.gson.annotations.SerializedName

data class Farmer(
    val address: String,
    val bvn: String,
    val city: String,
    val dob: String,
    @SerializedName("email_address")
    val emailAddress: String,
    @SerializedName("expiry_date")
    val expiryDate: String,
    @SerializedName("farmer_id")
    val farmerId: String,
    val fingerprint: String,
    @SerializedName("first_name")
    val firstName: String,
    val gender: String,
    @SerializedName("id_image")
    val idImage: String,
    @SerializedName("id_no")
    val idNo: String,
    @SerializedName("id_type")
    val idType: String,
    @SerializedName("issue_date")
    val issueDate: String,
    val lga: String,
    @SerializedName("marital_status")
    val maritalStatus: String,
    @SerializedName("middle_name")
    val middleName: String,
    @SerializedName("mobile_no")
    val mobileNo: String,
    val nationality: String,
    val occupation: String,
    @SerializedName("passport_photo")
    val passportPhoto: String,
    @SerializedName("reg_no")
    val regNo: String,
    @SerializedName("spouse_name")
    val spouseName: String,
    val state: String,
    val surname: String
)