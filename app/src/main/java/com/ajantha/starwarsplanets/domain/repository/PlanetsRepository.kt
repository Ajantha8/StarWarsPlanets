package com.ajantha.starwarsplanets.domain.repository

import com.ajantha.starwarsplanets.core.resource.ApiResult
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {

    suspend fun getPlanets(): Flow<ApiResult<List<PlanetModel>>>

}