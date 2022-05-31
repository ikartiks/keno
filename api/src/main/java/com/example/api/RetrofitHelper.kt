package com.example.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper() {


    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(getGSONConverterFactory())
            .client(getOkHttpClient())
            .build()
    }

    private fun getBaseUrl(): String {
        return APIDependencies.serverUrl
    }

    private fun getGSONConverterFactory(): GsonConverterFactory {
        val builder = GsonBuilder()
        val gson = builder.create()
        return GsonConverterFactory.create(gson)
    }

    private fun getOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY // Logs all - headers request and response

        val builder =  OkHttpClient.Builder()
        builder.apply {
            addInterceptor(interceptor)
        }
        return builder.build()
    }

    fun provideBeemAPIClient(): APIClient {
        return provideRetrofitInstance()
            .create(APIClient::class.java)
    }
}