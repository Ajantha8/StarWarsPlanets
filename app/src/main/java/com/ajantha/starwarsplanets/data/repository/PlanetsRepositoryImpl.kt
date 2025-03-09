package com.ajantha.starwarsplanets.data.repository

import com.ajantha.starwarsplanets.core.mapper.entitiesToPlanetModelList
import com.ajantha.starwarsplanets.core.mapper.modelsToPlanetEntityList
import com.ajantha.starwarsplanets.core.mapper.toPlanetModelList
import com.ajantha.starwarsplanets.core.resource.ApiResult
import com.ajantha.starwarsplanets.data.local.datasource.PlanetsLocalDataSource
import com.ajantha.starwarsplanets.data.remote.datasource.PlanetsRemoteDataSource
import com.ajantha.starwarsplanets.domain.repository.PlanetsRepository
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class PlanetsRepositoryImpl @Inject constructor(
    private val planetsRemoteDataSource: PlanetsRemoteDataSource,
    private val planetsLocalDataSource: PlanetsLocalDataSource
) : PlanetsRepository {

    override suspend fun getPlanets(): Flow<ApiResult<List<PlanetModel>>> = flow {
        emit(ApiResult.Loading)

        // Fetch and emit local data
        val localPlanetModels = planetsLocalDataSource.getPlanets()
            .entitiesToPlanetModelList()
        if (localPlanetModels.isNotEmpty()) {
            emit(ApiResult.Success(localPlanetModels))
        }

        // Fetch remote data
        val remotePlanetModels = planetsRemoteDataSource.getPlanets()
            .toPlanetModelList()

        // Update local data
        planetsLocalDataSource.updatePlanets(remotePlanetModels.modelsToPlanetEntityList())

        // Emit remote data
        emit(ApiResult.Success(remotePlanetModels))

    }.catch { e -> // Catch exceptions inside the flow
        e.printStackTrace()
        emit(ApiResult.Error(e))
    }
        .onCompletion {
            emit(ApiResult.Finally)
        }

}