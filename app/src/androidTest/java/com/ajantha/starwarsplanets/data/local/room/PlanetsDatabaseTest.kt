package com.ajantha.starwarsplanets.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import com.ajantha.starwarsplanets.data.local.room.entity.PlanetEntity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PlanetsDatabaseTest {
    private lateinit var db: PlanetsDatabase
    private lateinit var planetDao: PlanetDao
    private lateinit var gson: Gson

    @Before
    fun createDb() {
        gson = GsonBuilder().create()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            PlanetsDatabase::class.java
        )
            .build()

        planetDao = db.planetDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insert_and_get_planets_success() = runTest {
        planetDao.insertPlanets(planetEntities)

        val retrievedList = planetDao.getPlanets()

        assertEquals(
            planetEntities.size,
            retrievedList.size
        )
    }

    @Test
    @Throws(IOException::class)
    fun insert_and_delete_planets_success() = runTest {
        planetDao.insertPlanets(planetEntities)

        val retrievedList = planetDao.getPlanets()

        assertEquals(
            planetEntities.size,
            retrievedList.size
        )

        planetDao.deletePlanets()

        val retrievedListDeleted = planetDao.getPlanets()

        assertEquals(
            0,
            retrievedListDeleted.size
        )
    }

    @Test
    @Throws(IOException::class)
    fun update_planets_success() = runTest {
        planetDao.insertPlanets(listOf(planetEntities[1]))

        planetDao.updatePlanets(planetEntities)

        val retrievedList = planetDao.getPlanets()

        assertEquals(
            planetEntities.size,
            retrievedList.size
        )
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