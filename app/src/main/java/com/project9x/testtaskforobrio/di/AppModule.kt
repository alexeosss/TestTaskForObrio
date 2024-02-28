package com.project9x.testtaskforobrio.di

import android.content.Context
import androidx.room.Room
import com.project9x.testtaskforobrio.data.Repository
import com.project9x.testtaskforobrio.data.RepositoryImpl
import com.project9x.testtaskforobrio.data.db.LocalDB
import com.project9x.testtaskforobrio.data.db.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource) : Repository {
        return RepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalSource(@ApplicationContext context: Context): LocalDataSource {
        return Room.databaseBuilder(context, LocalDB::class.java, "db").build().dao()
    }



}