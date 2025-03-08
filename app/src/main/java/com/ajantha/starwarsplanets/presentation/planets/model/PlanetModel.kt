package com.ajantha.starwarsplanets.presentation.planets.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Immutable
@Serializable
@Parcelize
data class PlanetModel(
    val uuid: String = UUID.randomUUID()
        .toString(),
    val name: String,
    val climate: String,
    val orbitalPeriod: String,
    val gravity: String,
    var imageUrl: String
) : Parcelable