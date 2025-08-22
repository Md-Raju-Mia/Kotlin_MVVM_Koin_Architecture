package com.raju.mvvm_koin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import com.raju.mvvm_koin.repository.JobRepository
import androidx.lifecycle.viewModelScope

class JobsViewModel(private val jobRepository: JobRepository) : ViewModel() {

    val assignedDefectList = jobRepository.assignedDefectList

    fun fetchAssignedDefectList(mechanicPin: String) {
        viewModelScope.launch {
            jobRepository.fetchAssignedDefectList(mechanicPin)
        }
    }
}