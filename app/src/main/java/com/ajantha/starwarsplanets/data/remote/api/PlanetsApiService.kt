package com.ajantha.starwarsplanets.data.remote.api

import com.ajantha.starwarsplanets.domain.model.PlanetsResponse
import retrofit2.http.GET

interface PlanetsApiService {

    @GET("planets")
    suspend fun getPlanets(): PlanetsResponse

}