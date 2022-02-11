package com.example.hepsiburadacase.di

import com.example.hepsiburadacase.data.repository.CollectionRepositoryImpl
import com.example.hepsiburadacase.domain.repository.CollectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCollectionRepository(repository: CollectionRepositoryImpl): CollectionRepository
}