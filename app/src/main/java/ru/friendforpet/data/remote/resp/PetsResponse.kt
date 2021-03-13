package ru.friendforpet.data.remote.resp


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class PetsResponse : ArrayList<PetsResponse.PetsResponseItem>(){
    @Serializable
    data class PetsResponseItem(
        @SerialName("age")
        val age: Int = 0,
        @SerialName("city_id")
        val cityId: Int = 0,
        @SerialName("color")
        val color: Int = 0,
        @SerialName("description")
        val description: String = "",
        @SerialName("furr")
        val furr: Int = 0,
        @SerialName("gender")
        val gender: Int = 0,
        @SerialName("id")
        val id: Int = 0,
        @SerialName("name")
        val name: String = "",
        @SerialName("pet")
        val pet: Int = 0,
        @SerialName("pet_character")
        val petCharacter: Int = 0,
        @SerialName("photos")
        val photos: List<String> = listOf(),
        @SerialName("size")
        val size: Int = 0,
        @SerialName("tags")
        val tags: List<String> = listOf()
    )
}