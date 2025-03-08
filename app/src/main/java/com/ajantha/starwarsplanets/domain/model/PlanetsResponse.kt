package com.ajantha.starwarsplanets.domain.model

import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val planets: List<Planet>
) {
    data class Planet(
        val climate: String,
        val created: String,
        val diameter: String,
        val edited: String,
        val films: List<Any>,
        val gravity: String,
        val name: String,
        @SerializedName("orbital_period")
        val orbitalPeriod: String,
        val population: String,
        val residents: List<String>,
        @SerializedName("rotation_period")
        val rotationPeriod: String,
        @SerializedName("surface_water")
        val surfaceWater: String,
        val terrain: String,
        val url: String
    )
}