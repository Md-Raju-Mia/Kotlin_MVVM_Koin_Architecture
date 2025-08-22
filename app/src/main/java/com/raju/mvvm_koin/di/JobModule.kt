package com.raju.mvvm_koin.di

import com.raju.mvvm_koin.repository.JobRepository
import com.raju.mvvm_koin.repositoryImpl.JobRepositoryImpl
import com.raju.mvvm_koin.viewmodel.JobsViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel

val jobModule = module {
    single<JobRepository> { JobRepositoryImpl(androidContext()) }
    viewModel { JobsViewModel(get()) }
}