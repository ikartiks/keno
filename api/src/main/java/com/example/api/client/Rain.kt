package com.example.api.client

import com.google.gson.annotations.SerializedName


data class Rain(
    @SerializedName("3h") var threeH: Double? = null
)