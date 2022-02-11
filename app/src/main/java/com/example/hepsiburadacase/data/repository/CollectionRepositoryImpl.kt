package com.example.hepsiburadacase.data.repository

import com.example.hepsiburadacase.data.Result
import com.example.hepsiburadacase.data.datasource.CollectionRemoteDataSource
import com.example.hepsiburadacase.domain.entity.Collection
import com.example.hepsiburadacase.domain.repository.CollectionRepository
import com.example.hepsiburadacase.domain.usecase.CollectionParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val remoteDataSource: CollectionRemoteDataSource
) : CollectionRepository {

    override suspend fun fetchCollection(params: CollectionParams): Flow<Result<Collection>> =
        remoteDataSource.fetchCollection(params)
}