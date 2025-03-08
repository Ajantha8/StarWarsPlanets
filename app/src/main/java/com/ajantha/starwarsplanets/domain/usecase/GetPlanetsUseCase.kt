package com.ajantha.starwarsplanets.domain.usecase

import com.ajantha.starwarsplanets.core.resource.ApiResult
import com.ajantha.starwarsplanets.domain.repository.PlanetsRepository
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlinx.coroutines.flow.Flow

class GetPlanetsUseCase(
    private val repository: PlanetsRepository
) {

    suspend operator fun invoke(): Flow<ApiResult<List<PlanetModel>>> {
        return repository.getPlanets()
    }

}