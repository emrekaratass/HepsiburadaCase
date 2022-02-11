package com.example.hepsiburadacase.data.datasource

import com.example.hepsiburadacase.data.Result
import com.example.hepsiburadacase.domain.entity.Collection
import com.example.hepsiburadacase.domain.usecase.CollectionParams
import kotlinx.coroutines.flow.Flow

interface CollectionRemoteDataSource {
    suspend fun fetchCollection(params: CollectionParams): Flow<Result<Collection>>
}