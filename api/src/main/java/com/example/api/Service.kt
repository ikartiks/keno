package com.example.api

import com.example.api.client.CitiesResponse
import com.example.api.client.CityDetailResponse
import retrofit2.Response

class Service(private val APIClient: APIClient) {

    suspend fun fetchCities(): Response<CitiesResponse> {
        return APIClient.fetchCities()
    }

    suspend fun fetchCityDetails(id:String): Response<CityDetailResponse> {
        return APIClient.fetchCityDetails(id)
    }
}
