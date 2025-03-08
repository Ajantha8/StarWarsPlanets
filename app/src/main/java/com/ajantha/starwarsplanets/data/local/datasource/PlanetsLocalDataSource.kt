package com.ajantha.starwarsplanets.data.local.datasource

import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import com.ajantha.starwarsplanets.data.local.room.entity.PlanetEntity

class PlanetsLocalDataSource(private val planetDao: PlanetDao) {

    suspend fun getPlanets(): List<PlanetEntity> {
        return planetDao.getPlanets()
    }

    suspend fun updatePlanets(planets: List<PlanetEntity>) {
        return planetDao.updatePlanets(planets)
    }

}