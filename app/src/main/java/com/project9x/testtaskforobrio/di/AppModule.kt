package com.project9x.testtaskforobrio.di

import com.project9x.testtaskforobrio.data.Repository
import com.project9x.testtaskforobrio.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository() : Repository {
        return RepositoryImpl()
    }


}