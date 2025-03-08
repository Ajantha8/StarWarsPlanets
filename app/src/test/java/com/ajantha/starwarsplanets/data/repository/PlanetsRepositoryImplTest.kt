package com.ajantha.starwarsplanets.data.repository

import com.ajantha.starwarsplanets.core.resource.ApiResult
import com.ajantha.starwarsplanets.data.local.datasource.PlanetsLocalDataSource
import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import com.ajantha.starwarsplanets.data.local.room.entity.PlanetEntity
import com.ajantha.starwarsplanets.data.remote.api.PlanetsApiService
import com.ajantha.starwarsplanets.data.remote.datasource.PlanetsRemoteDataSource
import com.ajantha.starwarsplanets.domain.model.PlanetsResponse
import com.ajantha.starwarsplanets.domain.repository.PlanetsRepository
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class PlanetsRepositoryImplTest {
    private val planetsApiService: PlanetsApiService = mock()
    private val planetDao: PlanetDao = mock()
    private lateinit var planetsRemoteDataSource: PlanetsRemoteDataSource
    private lateinit var planetsLocalDataSource: PlanetsLocalDataSource
    private lateinit var planetsRepository: PlanetsRepository

    @Before
    fun setUp() {
        planetsRemoteDataSource = PlanetsRemoteDataSource(planetsApiService)
        planetsLocalDataSource = PlanetsLocalDataSource(planetDao)
        planetsRepository = PlanetsRepositoryImpl(
            planetsRemoteDataSource = planetsRemoteDataSource,
            planetsLocalDataSource = planetsLocalDataSource
        )
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

        // Collect the emitted results
        val expectedValues = mutableListOf<ApiResult<List<PlanetModel>>>()
        val job = launch {
            planetsRepository.getPlanets()
                .collect {
                    expectedValues.add(it)
                }
        }
        advanceUntilIdle()
        job.cancel()

        // Check expected result count (Loading, Success, Success, Finally)
        assertEquals(
            4,
            expectedValues.size
        )

        // check expected results order is match
        assert(expectedValues[0] is ApiResult.Loading)
        assert(expectedValues[1] is ApiResult.Success)
        assert(expectedValues[2] is ApiResult.Success)
        assert(expectedValues[3] is ApiResult.Finally)

        // check expected list size is match for 1st emit
        val localPlanets = (expectedValues[1] as ApiResult.Success).data
        assertEquals(
            planetEntities.size,
            localPlanets.size
        )
    }

    @Test
    fun `get planets error`() = runTest {
        // Mock the API service to return the complete PlanetsResponse
        `when`(planetsApiService.getPlanets()).thenThrow(RuntimeException("Network error"))

        // Mock the DAO to return the PlanetEntities
        `when`(planetDao.getPlanets()).thenReturn(
            planetEntities
        )
        // Collect the emitted results
        val expectedValues = mutableListOf<ApiResult<List<PlanetModel>>>()
        val job = launch {
            planetsRepository.getPlanets()
                .collect {
                    expectedValues.add(it)
                }
        }
        advanceUntilIdle()
        job.cancel()

        // Check expected result count (Loading, Success, Error, Finally)
        assertEquals(
            4,
            expectedValues.size
        )
        // check expected results order is match
        assert(expectedValues[0] is ApiResult.Loading)
        assert(expectedValues[1] is ApiResult.Success)
        assert(expectedValues[2] is ApiResult.Error)
        assert(expectedValues[3] is ApiResult.Finally)

        // check error message is match
        val error = expectedValues[2] as ApiResult.Error
        assertTrue(error.exception.message.equals("Network error"))
    }
}

val planetEntities = listOf(
    PlanetEntity(
        name = "Tatooine",
        climate = "arid",
        orbitalPeriod = "304",
        gravity = "1 standard",
        imageUrl = "https://picsum.photos/id/14/300/300"
    ),
    PlanetEntity(
        name = "Yavin IV",
        climate = "temperate, tropical",
        orbitalPeriod = "4818",
        gravity = "1 standard",
        imageUrl = "https://picsum.photos/id/15/300/300"
    ),
    PlanetEntity(
        name = "Hoth",
        climate = "frozen",
        orbitalPeriod = "549",
        gravity = "1.1 standard",
        imageUrl = "https://picsum.photos/id/16/300/300"
    )
)

val planets = listOf(
    PlanetsResponse.Planet(
        name = "Endor",
        climate = "temperate",
        orbitalPeriod = "402",
        gravity = "0.85 standard",
        created = "2014-12-10T11:50:29.349000Z",
        diameter = "4900",
        edited = "2014-12-20T20:58:18.429000Z",
        films = listOf("https://swapi.dev/api/films/3/"),
        population = "30000000",
        residents = listOf("https://swapi.dev/api/people/30/"),
        rotationPeriod = "18",
        surfaceWater = "8",
        terrain = "forests, mountains, lakes",
        url = "https://swapi.dev/api/planets/7/"
    ),
    PlanetsResponse.Planet(
        name = "Naboo",
        climate = "temperate",
        orbitalPeriod = "312",
        gravity = "1 standard",
        created = "2014-12-10T11:52:31.066000Z",
        diameter = "12120",
        edited = "2014-12-20T20:58:18.430000Z",
        films = listOf(
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/4/",
            "https://swapi.dev/api/films/5/",
            "https://swapi.dev/api/films/6/"
        ),
        population = "4500000000",
        residents = listOf(
            "https://swapi.dev/api/people/3/",
            "https://swapi.dev/api/people/21/",
            "https://swapi.dev/api/people/35/",
            "https://swapi.dev/api/people/36/",
            "https://swapi.dev/api/people/37/",
            "https://swapi.dev/api/people/38/",
            "https://swapi.dev/api/people/39/",
            "https://swapi.dev/api/people/42/",
            "https://swapi.dev/api/people/60/",
            "https://swapi.dev/api/people/61/",
            "https://swapi.dev/api/people/66/"
        ),
        rotationPeriod = "26",
        surfaceWater = "12",
        terrain = "grassy hills, swamps, forests, mountains",
        url = "https://swapi.dev/api/planets/8/"
    ),
    PlanetsResponse.Planet(
        name = "Coruscant",
        climate = "temperate",
        orbitalPeriod = "368",
        gravity = "1 standard",
        created = "2014-12-10T11:54:13.921000Z",
        diameter = "12240",
        edited = "2014-12-20T20:58:18.432000Z",
        films = listOf(
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/4/",
            "https://swapi.dev/api/films/5/",
            "https://swapi.dev/api/films/6/"
        ),
        population = "1000000000000",
        residents = listOf(
            "https://swapi.dev/api/people/34/",
            "https://swapi.dev/api/people/55/",
            "https://swapi.dev/api/people/74/"
        ),
        rotationPeriod = "24",
        surfaceWater = "unknown",
        terrain = "cityscape, mountains",
        url = "https://swapi.dev/api/planets/9/"
    )
)

val planetsResponse = PlanetsResponse(
    count = 10,
    next = null,
    previous = null,
    planets = planets
)