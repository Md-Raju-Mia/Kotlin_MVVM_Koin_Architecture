package com.raju.mvvm_koin.api.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.raju.mvvm_koin.api.response.AssignedDefectResponse

interface RetrofitAPI {


    @GET("ApiMechanic/AssignedDefectList")
    fun getAssignedDefect(@Query("mechanic_pin") mechanicPin: String?): Call<AssignedDefectResponse>
}