package com.raju.mvvm_koin.repository

import androidx.lifecycle.LiveData
import com.raju.mvvm_koin.api.response.AssignedDefectResponse

interface JobRepository {

    val assignedDefectList: LiveData<AssignedDefectResponse?>


    suspend fun fetchAssignedDefectList(mechanicPin: String)
}