package com.ajantha.starwarsplanets.presentation.planets

import com.ajantha.starwarsplanets.data.local.datasource.PlanetsLocalDataSource
import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import com.ajantha.starwarsplanets.data.remote.api.PlanetsApiService
import com.ajantha.starwarsplanets.data.remote.datasource.PlanetsRemoteDataSource
import com.ajantha.starwarsplanets.data.repository.PlanetsRepositoryImpl
import com.ajantha.starwarsplanets.data.repository.planetEntities
import com.ajantha.starwarsplanets.data.repository.planetsResponse
import com.ajantha.starwarsplanets.domain.repository.PlanetsRepository
import com.ajantha.starwarsplanets.domain.usecase.GetPlanetsUseCase
import com.ajantha.starwarsplanets.resources.MainDispatcherRule
import com.ajantha.starwarsplanets.resources.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class PlanetsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var dispatcherProvider: TestDispatcherProvider
    private val planetsApiService: PlanetsApiService = mock()
    private val planetDao: PlanetDao = mock()
    private lateinit var planetsRemoteDataSource: PlanetsRemoteDataSource
    private lateinit var planetsLocalDataSource: PlanetsLocalDataSource
    private lateinit var planetsRepository: PlanetsRepository
    private lateinit var getPlanetsUseCase: GetPlanetsUseCase

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        planetsRemoteDataSource = PlanetsRemoteDataSource(planetsApiService)
        planetsLocalDataSource = PlanetsLocalDataSource(planetDao)
        planetsRepository = PlanetsRepositoryImpl(
            planetsRemoteDataSource,
            planetsLocalDataSource
        )
        getPlanetsUseCase = GetPlanetsUseCase(planetsRepository)
    }

    @Test
    fun `get planets success`() = runTest {
        // Mock the API service to return the complete PlanetsResponse
        `when`(planetsApiService.getPlanets()).thenReturn(
            planetsResponse
        )

        // Mock the DAO to return the PlanetEntities
        `when`(planetDao.getPlanets()).thenReturn(
            planetEntities
        )

        val viewModel = PlanetsViewModel(
            getPlanetsUseCase = getPlanetsUseCase,
            dispatcherProvider = dispatcherProvider
        )

        // check expected list size is match
        assertEquals(
            planetsResponse.planets.size,
            viewModel.uiState.value.planets.size
        )
    }

    @Test
    fun `get planets error`() = runTest {
        // Mock the DAO to return the PlanetEntities
        `when`(planetDao.getPlanets()).thenThrow(RuntimeException("Database error"))

        val viewModel = PlanetsViewModel(
            getPlanetsUseCase = getPlanetsUseCase,
            dispatcherProvider = dispatcherProvider
        )

        // check planets list is empty
        assert(viewModel.uiState.value.planets.isEmpty())

    }

}