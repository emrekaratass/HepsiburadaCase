package com.example.hepsiburadacase.data.entity

import com.example.hepsiburadacase.util.entity.RemoteDataSourceItem

data class CollectionResponse(
    val results: List<ArticleResponse>? = null
) : RemoteDataSourceItem