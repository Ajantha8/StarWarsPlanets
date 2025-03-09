package com.ajantha.starwarsplanets.di

import com.ajantha.starwarsplanets.domain.repository.PlanetsRepository
import com.ajantha.starwarsplanets.domain.usecase.GetPlanetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPlanetsUseCase(planetsRepository: PlanetsRepository): GetPlanetsUseCase = GetPlanetsUseCase(planetsRepository)

}