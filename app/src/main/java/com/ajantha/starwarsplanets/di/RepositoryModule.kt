package com.ajantha.starwarsplanets.di

import com.ajantha.starwarsplanets.data.repository.PlanetsRepositoryImpl
import com.ajantha.starwarsplanets.domain.repository.PlanetsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePlanetsRepository(planetsRepositoryImpl: PlanetsRepositoryImpl): PlanetsRepository

}