package com.raju.mvvm_koin.repositoryImpl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.await
import com.raju.mvvm_koin.api.client.RetrofitClient
import com.raju.mvvm_koin.constant.Config
import com.raju.mvvm_koin.api.response.AssignedDefectResponse
import android.content.Context
import com.raju.mvvm_koin.repository.JobRepository
import androidx.lifecycle.LiveData

class JobRepositoryImpl(val context: Context) : JobRepository {

    private val _assignedDefectList = MutableLiveData<AssignedDefectResponse?>()

    override val assignedDefectList: LiveData<AssignedDefectResponse?>
        get() = _assignedDefectList


    override suspend fun fetchAssignedDefectList(mechanicPin: String) {
        try {
            val response = RetrofitClient.getInstance(Config.IFLEET_BRAC_BASE_URL)
                ?.getAssignedDefect(mechanicPin)?.await()

            Log.e("AssignedDefectResponse size", response?.response?.size.toString())
            _assignedDefectList.postValue(response)

        } catch (e: Exception) {
            e.printStackTrace()
            _assignedDefectList.postValue(null)
        }

    }
}