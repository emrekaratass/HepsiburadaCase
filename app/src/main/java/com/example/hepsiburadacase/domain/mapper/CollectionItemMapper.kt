package com.example.hepsiburadacase.domain.mapper

import com.example.hepsiburadacase.domain.entity.Collection
import com.example.hepsiburadacase.ui.entity.ArticleViewItem
import com.example.hepsiburadacase.ui.entity.CollectionViewItem
import com.example.hepsiburadacase.util.extension.ignoreNull
import com.example.hepsiburadacase.util.mapper.Mapper

class CollectionItemMapper : Mapper<Collection, CollectionViewItem> {

    override suspend fun map(item: Collection): CollectionViewItem {
        val articles: ArrayList<ArticleViewItem> = arrayListOf()
        item.articles.forEach { response ->
            val articleItem = ArticleViewItem(
                artworkUrl100 = response.artworkUrl100.ignoreNull(),
                collectionName = response.collectionName.ignoreNull(),
                collectionPrice = response.collectionPrice.ignoreNull(),
                releaseDate = response.releaseDate.ignoreNull(),
                description = response.description.ignoreNull()
            )
            articles.add(articleItem)
        }
        return CollectionViewItem(articles)
    }
}