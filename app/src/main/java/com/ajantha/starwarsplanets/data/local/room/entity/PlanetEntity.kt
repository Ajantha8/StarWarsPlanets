package com.ajantha.starwarsplanets.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "climate")
    val climate: String,
    @ColumnInfo(name = "orbitalPeriod")
    val orbitalPeriod: String,
    @ColumnInfo(name = "gravity")
    val gravity: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)