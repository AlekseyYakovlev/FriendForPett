package ru.friendforpet.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import ru.friendforpet.data.remote.resp.CityResponse
import ru.friendforpet.data.remote.resp.PetsResponse

interface RestService {
    //POST https://documents.zenithapps.net/api/pets/get
    @POST("pets/get")
    suspend fun getPets(

    ): List<PetsResponse.PetsResponseItem>

    //https://documents.zenithapps.net/api/pets/city
    @GET("pets/city")
    suspend fun getCities(): CityResponse
}