package com.example.hepsiburadacase.data.service

import com.example.hepsiburadacase.data.entity.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CollectionService {

    @GET("search?")
    suspend fun getCollection(
        @QueryMap queryMap: Map<String, String>
    ): CollectionResponse
}