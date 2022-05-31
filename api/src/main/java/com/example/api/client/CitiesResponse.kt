package com.example.api.client

import com.google.gson.annotations.SerializedName


data class CitiesResponse(

    @SerializedName("cod") var cod: String? = null,
    @SerializedName("calctime") var calctime: Double? = null,
    @SerializedName("cnt") var cnt: Int? = null,
    @SerializedName("list") var cities: ArrayList<City> = arrayListOf()

)