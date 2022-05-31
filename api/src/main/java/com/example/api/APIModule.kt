package com.example.api

import org.koin.dsl.module

/**
 * Provides repo module for Koin to be fetched from app
 */
val apiModule = module {
    single { provideRetrofitHelper() }
    single { provideBeemClient(get()) }
    single { Service(get()) }
}

fun provideRetrofitHelper(): RetrofitHelper {
    return RetrofitHelper()
}

fun provideBeemClient(retrofitHelper: RetrofitHelper): APIClient {
    return  retrofitHelper.provideBeemAPIClient()
}
