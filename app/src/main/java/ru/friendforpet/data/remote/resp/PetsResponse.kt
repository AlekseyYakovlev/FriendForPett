package ru.friendforpet.data.remote.resp


import com.squareup.moshi.Json

class PetsResponse : ArrayList<PetsResponse.PetsResponseItem>(){
    data class PetsResponseItem(
        @Json(name = "age")
        val age: Int = 0,
        @Json(name = "city_id")
        val cityId: Int = 0,
        @Json(name = "color")
        val color: Int = 0,
        @Json(name = "description")
        val description: String = "",
        @Json(name = "furr")
        val furr: Int = 0,
        @Json(name = "gender")
        val gender: Int = 0,
        @Json(name = "id")
        val id: Int = 0,
        @Json(name = "name")
        val name: String = "",
        @Json(name = "pet")
        val pet: Int = 0,
        @Json(name = "pet_character")
        val petCharacter: Int = 0,
        @Json(name = "photos")
        val photos: List<Photo> = listOf(),
        @Json(name = "size")
        val size: Int = 0,
        @Json(name = "tags")
        val tags: List<Tag> = listOf()
    ) {
        data class Photo(
            @Json(name = "id")
            val id: Int = 0,
            @Json(name = "pet_id")
            val petId: Int = 0,
            @Json(name = "photo_url")
            val photoUrl: String = ""
        )
    
        data class Tag(
            @Json(name = "id")
            val id: Int = 0,
            @Json(name = "pet_id")
            val petId: Int = 0,
            @Json(name = "tag")
            val tag: String = ""
        )
    }
}