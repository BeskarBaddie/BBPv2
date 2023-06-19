package com.honours.bbpv2

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("idusers") val id: Int,
    @SerializedName("name") val firstname: String,
    @SerializedName("password") val password: String,
    @SerializedName("type") val type: String
)