package com.ajantha.starwarsplanets.data.remote.datasource

import com.ajantha.starwarsplanets.data.remote.api.PlanetsApiService
import com.ajantha.starwarsplanets.domain.model.PlanetsResponse

class PlanetsRemoteDataSource(private val planetsApiService: PlanetsApiService) {

    suspend fun getPlanets(): List<PlanetsResponse.Planet> {
        return planetsApiService.getPlanets().planets
    }

}