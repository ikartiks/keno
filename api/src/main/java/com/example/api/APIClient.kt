package com.example.api

import com.example.api.client.CitiesResponse
import com.example.api.client.CityDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {

    @GET("weather/list")
    suspend fun fetchCities(): Response<CitiesResponse>

    @GET("weather/{id}")
    suspend fun fetchCityDetails(@Path("id") id:String): Response<CityDetailResponse>
}