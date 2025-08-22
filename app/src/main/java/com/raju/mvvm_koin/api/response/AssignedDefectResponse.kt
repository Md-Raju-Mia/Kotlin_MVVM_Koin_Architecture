package com.raju.mvvm_koin.api.response

import com.google.gson.annotations.SerializedName

data class AssignedDefectResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("response") var response: List<AssignedDefectResponseItem>?,
    @SerializedName("message") var message: String?
)
