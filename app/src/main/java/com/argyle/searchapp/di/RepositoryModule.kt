package com.argyle.searchapp.di

import com.argyle.searchapp.data.repository.SearchRepository
import com.argyle.searchapp.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(
        artistsRepository: SearchRepositoryImpl
    ): SearchRepository
}