package com.example.hepsiburadacase.data.mapper

import com.example.hepsiburadacase.data.entity.CollectionResponse
import com.example.hepsiburadacase.domain.entity.Article
import com.example.hepsiburadacase.domain.entity.Collection
import com.example.hepsiburadacase.util.mapper.Mapper

class CollectionDomainMapper : Mapper<CollectionResponse, Collection> {

    override suspend fun map(item: CollectionResponse): Collection {
        val articles: ArrayList<Article> = arrayListOf()
        item.results?.forEach { response ->
            val article = Article(
                artworkUrl100 = response.artworkUrl100,
                collectionName = response.collectionName,
                collectionPrice = response.collectionPrice,
                releaseDate = response.releaseDate,
                description = response.description
            )
            articles.add(article)
        }
        return Collection(articles)
    }
}