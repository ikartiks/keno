package com.example.api.client

import com.google.gson.annotations.SerializedName


data class CityDetailResponse(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("coord") var coord: Coord? = Coord(),
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("wind") var wind: Wind? = Wind(),
    @SerializedName("rain") var rain: Rain? = Rain(),
    @SerializedName("clouds") var clouds: Clouds? = Clouds(),
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("sys") var sys: Sys? = Sys()

)