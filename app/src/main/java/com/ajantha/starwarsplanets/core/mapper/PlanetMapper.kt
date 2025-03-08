package com.ajantha.starwarsplanets.core.mapper

import com.ajantha.starwarsplanets.data.local.room.entity.PlanetEntity
import com.ajantha.starwarsplanets.domain.model.PlanetsResponse
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlin.random.Random

fun List<PlanetsResponse.Planet>.toPlanetModelList(): List<PlanetModel> {
    return this.map {
        PlanetModel(
            name = it.name,
            climate = it.climate,
            orbitalPeriod = it.orbitalPeriod,
            gravity = it.gravity,
            imageUrl = "https://picsum.photos/id/${Random.nextInt(1000)}/300/300" // Set dynamic image url with random image id
        )
    }
}

fun List<PlanetModel>.modelsToPlanetEntityList(): List<PlanetEntity> {
    return this.map {
        PlanetEntity(
            name = it.name,
            climate = it.climate,
            orbitalPeriod = it.orbitalPeriod,
            gravity = it.gravity,
            imageUrl = it.imageUrl
        )
    }
}

fun List<PlanetEntity>.entitiesToPlanetModelList(): List<PlanetModel> {
    return this.map {
        PlanetModel(
            name = it.name,
            climate = it.climate,
            orbitalPeriod = it.orbitalPeriod,
            gravity = it.gravity,
            imageUrl = it.imageUrl
        )
    }
}