package com.example.keno

import com.example.api.Service
import com.example.api.client.CitiesResponse
import com.example.api.client.City
import java.lang.RuntimeException

class CitiesRepo(val service: Service) {

    //Basic caching only, can have a good better logic here
    var citiesResponse:CitiesResponse?=null

    suspend fun getCitiesList():List<City> {

        //Basic caching only, can have a good better logic here
        citiesResponse?.let {
            return it.cities
        }

        val response = service.fetchCities()
        return if(response.isSuccessful){
            citiesResponse = response.body()!!
            citiesResponse!!.cities
        }else{
            throw RuntimeException("Error fetching cities")
        }
    }
}