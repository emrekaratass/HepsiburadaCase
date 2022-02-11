package com.example.hepsiburadacase.domain.repository

import com.example.hepsiburadacase.data.Result
import com.example.hepsiburadacase.domain.entity.Collection
import com.example.hepsiburadacase.domain.usecase.CollectionParams
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    suspend fun fetchCollection(params: CollectionParams): Flow<Result<Collection>>
}