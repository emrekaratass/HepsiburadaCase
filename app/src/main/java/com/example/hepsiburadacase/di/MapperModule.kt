package com.example.hepsiburadacase.di

import com.example.hepsiburadacase.data.mapper.CollectionDomainMapper
import com.example.hepsiburadacase.domain.mapper.CollectionItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun providesCollectionDomainMapper(): CollectionDomainMapper = CollectionDomainMapper()

    @Provides
    fun providesCollectionItemMapper(): CollectionItemMapper = CollectionItemMapper()
}