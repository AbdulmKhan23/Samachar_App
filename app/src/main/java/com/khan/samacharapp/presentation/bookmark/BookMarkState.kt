package com.khan.samacharapp.presentation.bookmark

import com.khan.samacharapp.domain.model.Article

data class BookMarkState (
    val articles: List<Article> = emptyList(),
){
}