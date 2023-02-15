package com.greenv.feb14.network

import com.greenv.feb14.response.RamResponse
import retrofit2.http.GET

interface RamApi {

    @GET("character")
    suspend fun getCharacters(): RamResponse
}