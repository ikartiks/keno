package com.example.keno

import androidx.multidex.MultiDexApplication
import com.example.api.apiModule
import com.example.keno.vm.FragmentCityDetailViewModel
import com.example.keno.vm.FragmentCityListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single { CitiesRepo(get()) }
            viewModel { FragmentCityDetailViewModel(get()) }
            viewModel { FragmentCityListViewModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModule, apiModule)
        }
    }
}
