package com.ajantha.starwarsplanets.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ajantha.starwarsplanets.data.local.room.entity.PlanetEntity

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets ORDER BY id ASC")
    suspend fun getPlanets(): List<PlanetEntity>

    @Query("DELETE FROM planets")
    suspend fun deletePlanets()

    @Transaction
    suspend fun updatePlanets(planets: List<PlanetEntity>) {
        deletePlanets()
        insertPlanets(planets)
    }

}