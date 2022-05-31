package com.example.keno.adapters.vh

import com.example.api.client.City

data class CityDisplay(val name:String,val whether:String,val city: City)


fun City.toDisplay() = CityDisplay(this.name?:"",this.getWhether(),this)

fun City.getWhether():String{
    val sb = StringBuilder()
    this.weather.forEachIndexed { index, weather ->
        if(index!=this.weather.size-1){
            sb.append(weather.description+", ")
        }else{
            sb.append(weather.description)
        }
    }
    return sb.toString()
}