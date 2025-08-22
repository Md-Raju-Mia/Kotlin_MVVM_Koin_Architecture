package com.raju.mvvm_koin.api.client

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.raju.mvvm_koin.BuildConfig


object RetrofitClient {
    private const val TAG = "RetrofitClient"
    private val retrofitInstances = HashMap<String, Retrofit>()

    fun getInstance(baseURL: String): RetrofitAPI? {
        return try {
            if (!retrofitInstances.containsKey(baseURL)) {
                val builder =
                    OkHttpClient.Builder()
                        .writeTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .connectTimeout(5, TimeUnit.MINUTES)

                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    builder.addInterceptor(loggingInterceptor)
                }

                val okHttpClient = builder.build()

                val gson = GsonBuilder().setStrictness(Strictness.LENIENT).create()

                val retrofit = Retrofit.Builder().baseUrl(baseURL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()

                retrofitInstances[baseURL] = retrofit
            }
            retrofitInstances[baseURL]?.create(RetrofitAPI::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "getInstance: ${e.message}", e)
            null
        }
    }
}