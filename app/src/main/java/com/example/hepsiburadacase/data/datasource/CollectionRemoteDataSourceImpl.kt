package com.example.hepsiburadacase.data.datasource

import com.example.hepsiburadacase.data.Result
import com.example.hepsiburadacase.data.mapper.CollectionDomainMapper
import com.example.hepsiburadacase.data.service.CollectionService
import com.example.hepsiburadacase.domain.usecase.CollectionParams
import com.example.hepsiburadacase.util.extension.ignoreNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import com.example.hepsiburadacase.domain.entity.Collection
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CollectionRemoteDataSourceImpl @Inject constructor(
    private val service: CollectionService,
    private val mapper: CollectionDomainMapper
) : CollectionRemoteDataSource {

    override suspend fun fetchCollection(params: CollectionParams): Flow<Result<Collection>> =
        flow<Result<Collection>> {
            val queryMap: HashMap<String, String> = hashMapOf()
            queryMap[TERM] = params.query.ignoreNull()
            queryMap[ENTITY] = params.entity.ignoreNull()
            queryMap[PAGE_OFFSET] = params.page.toString()
            queryMap[PAGE_SIZE] = "20"

            val response = service.getCollection(queryMap)
            val collection = mapper.map(response)

            emit(Result.Success(collection))
        }.catch { throwable ->
            emit(Result.Error(Exception(throwable)))
        }.flowOn(Dispatchers.IO)

    companion object {
        private const val TERM = "term"
        private const val ENTITY = "entity"
        private const val PAGE_OFFSET = "offset"
        private const val PAGE_SIZE = "limit"
    }
}
