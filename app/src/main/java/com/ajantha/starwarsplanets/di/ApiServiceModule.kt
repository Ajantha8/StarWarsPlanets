package com.ajantha.starwarsplanets.di

import com.ajantha.starwarsplanets.data.remote.api.PlanetsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun providePlanetsApiService(retrofit: Retrofit): PlanetsApiService = retrofit.create(PlanetsApiService::class.java)

}