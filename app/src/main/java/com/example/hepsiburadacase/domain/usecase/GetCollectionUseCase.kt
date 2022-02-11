package com.example.hepsiburadacase.domain.usecase

import com.example.hepsiburadacase.data.succeeded
import com.example.hepsiburadacase.data.Result
import com.example.hepsiburadacase.domain.mapper.CollectionItemMapper
import com.example.hepsiburadacase.domain.repository.CollectionRepository
import com.example.hepsiburadacase.ui.entity.CollectionViewItem
import com.example.hepsiburadacase.util.usecase.Params
import com.example.hepsiburadacase.util.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository,
    private val mapper: CollectionItemMapper
) : UseCase.FlowUseCase<CollectionParams, CollectionViewItem> {

    override suspend fun execute(params: CollectionParams): Flow<Result<CollectionViewItem>> {
        return repository.fetchCollection(params)
            .flatMapLatest { result ->
                flow {
                    if (result.succeeded) {
                        result as Result.Success
                        val viewItem = mapper.map(result.data)
                        emit(Result.Success(viewItem))
                        return@flow
                    }
                    result as Result.Error
                    emit(Result.Error(result.exception))
                }
            }.catch { throwable ->
                emit(Result.Error(Exception(throwable)))
            }.flowOn(Dispatchers.IO)
    }
}

data class CollectionParams(
    val page: Int,
    val query: String?,
    val entity: String?
) : Params()