package com.example.hepsiburadacase.domain.entity

import com.example.hepsiburadacase.util.entity.DomainItem

data class Collection(
    val articles: List<Article>
) : DomainItem