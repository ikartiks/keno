package com.example.keno.vm

import androidx.lifecycle.ViewModel
import com.example.api.client.CityDetailResponse
import com.example.keno.CitiesRepo
import org.koin.core.component.KoinComponent


class FragmentCityDetailViewModel(private val repo: CitiesRepo) : ViewModel(), KoinComponent {

    suspend fun getCityDetails(id:String):CityDetailResponse{
        return repo.getCityDetails(id)
    }
}
