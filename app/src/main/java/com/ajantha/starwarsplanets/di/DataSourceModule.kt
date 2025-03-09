package com.ajantha.starwarsplanets.di

import com.ajantha.starwarsplanets.data.local.datasource.PlanetsLocalDataSource
import com.ajantha.starwarsplanets.data.local.room.dao.PlanetDao
import com.ajantha.starwarsplanets.data.remote.api.PlanetsApiService
import com.ajantha.starwarsplanets.data.remote.datasource.PlanetsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providePlanetsRemoteDataSource(planetsApiService: PlanetsApiService): PlanetsRemoteDataSource = PlanetsRemoteDataSource(planetsApiService)

    @Provides
    @Singleton
    fun providePlanetsLocalDataSource(planetDao: PlanetDao): PlanetsLocalDataSource = PlanetsLocalDataSource(planetDao)

}