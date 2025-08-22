package com.raju.mvvm_koin

import android.app.Application
import android.util.Log
import com.raju.mvvm_koin.di.jobModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class MainApplication : Application() {

    override fun onCreate(){
        super.onCreate()
        Log.e("MainApplication","onCreate")

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(jobModule))
        }
    }
}