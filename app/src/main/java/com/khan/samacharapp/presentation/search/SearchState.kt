package com.khan.samacharapp.presentation.search

import androidx.paging.PagingData
import com.khan.samacharapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery:String ="",
    val articles:Flow<PagingData<Article>>? = null
) {
}