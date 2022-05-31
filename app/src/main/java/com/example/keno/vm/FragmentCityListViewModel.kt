package com.example.keno.vm

import androidx.lifecycle.ViewModel
import com.example.api.Service
import com.example.api.client.City
import com.example.keno.CitiesRepo
import com.example.keno.adapters.vh.CityDisplay
import com.example.keno.adapters.vh.toDisplay
import com.example.keno.base.ResProvider
import com.example.keno.utility.Preferences
import org.koin.core.component.KoinComponent

class FragmentCityListViewModel(private val repo: CitiesRepo) : ViewModel(), KoinComponent {

    suspend fun getCitiesList():List<CityDisplay> {
        return repo.getCitiesList().map { it.toDisplay() }
    }
}
