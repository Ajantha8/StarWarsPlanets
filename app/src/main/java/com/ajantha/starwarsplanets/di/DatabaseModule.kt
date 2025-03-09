package com.ajantha.starwarsplanets.di

import android.app.Application
import androidx.room.Room
import com.ajantha.starwarsplanets.data.local.room.PlanetsDatabase
import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): PlanetsDatabase {
        return Room.databaseBuilder(
            app,
            PlanetsDatabase::class.java,
            "planets_database"
        )
            .build()
    }

    @Provides
    @Singleton
    fun providePlanetDao(db: PlanetsDatabase): PlanetDao {
        return db.planetDao
    }

}