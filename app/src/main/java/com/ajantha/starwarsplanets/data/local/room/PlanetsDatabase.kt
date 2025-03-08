package com.ajantha.starwarsplanets.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import com.ajantha.starwarsplanets.data.local.room.entity.PlanetEntity

@Database(
    entities = [PlanetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PlanetsDatabase : RoomDatabase() {
    abstract val planetDao: PlanetDao
}