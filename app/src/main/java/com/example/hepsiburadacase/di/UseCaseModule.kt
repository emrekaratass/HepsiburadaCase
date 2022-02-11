package com.example.hepsiburadacase.di

import com.example.hepsiburadacase.domain.usecase.GetCollectionUseCase
import com.example.hepsiburadacase.util.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetCollectionUseCase(useCase: GetCollectionUseCase): UseCase
}