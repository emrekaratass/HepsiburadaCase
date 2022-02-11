package com.example.hepsiburadacase.util.usecase

import com.example.hepsiburadacase.data.Result
import com.example.hepsiburadacase.ui.entity.CollectionViewItem
import kotlinx.coroutines.flow.Flow

interface UseCase {

    @FunctionalInterface
    interface FlowUseCase<in P, out T> : UseCase where P : Params {
        suspend fun execute(params: P): Flow<Result<CollectionViewItem>>
    }
}

abstract class Params