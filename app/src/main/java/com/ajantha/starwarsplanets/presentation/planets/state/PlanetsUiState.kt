package com.ajantha.starwarsplanets.presentation.planets.state

import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel

data class PlanetsUiState(
    val planets: List<PlanetModel> = emptyList(),
    val loading: Boolean = false,
    val errorMessage: String? = null
)