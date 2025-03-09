package com.ajantha.starwarsplanets.ui.navigation

import androidx.annotation.Keep
import com.ajantha.starwarsplanets.presentation.planets.model.PlanetModel
import kotlinx.serialization.Serializable

@Keep
@Serializable
object Planets

@Keep
@Serializable
data class PlanetDetail(
    val planet: PlanetModel
)