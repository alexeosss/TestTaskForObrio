package com.project9x.testtaskforobrio.di

import android.content.Context
import androidx.room.Room
import com.project9x.testtaskforobrio.data.Repository
import com.project9x.testtaskforobrio.data.RepositoryImpl
import com.project9x.testtaskforobrio.data.local.LocalDB
import com.project9x.testtaskforobrio.data.local.LocalDataSource
import com.project9x.testtaskforobrio.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalSource(@ApplicationContext context: Context): LocalDataSource {
        return Room.databaseBuilder(context, LocalDB::class.java, "db").build().dao()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return Retrofit.Builder()
            .baseUrl("https://api.coindesk.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(RemoteDataSource::class.java)
    }


}