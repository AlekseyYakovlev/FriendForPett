package ru.friendforpet.data.remote

import retrofit2.http.*
import ru.friendforpet.data.remote.resp.CityResponse
import ru.friendforpet.data.remote.resp.PetsResponse

interface RestService {
    //POST https://documents.zenithapps.net/api/pets/get
    @POST("pets/get")
    suspend fun getPets(

    ): PetsResponse

    //https://documents.zenithapps.net/api/pets/city
    @GET("pets/city")
    suspend fun getCities(): CityResponse
}