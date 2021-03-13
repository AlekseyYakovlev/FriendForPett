package ru.friendforpet.data.remote.resp


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class CityResponse : ArrayList<CityResponse.CityResponseItem>(){
    @Serializable
    data class CityResponseItem(
        @SerialName("id")
        val id: Int = 0,
        @SerialName("name")
        val name: String = ""
    )
}