package com.khan.samacharapp.domain.useCase.news

import androidx.paging.PagingData
import com.khan.samacharapp.domain.Repository.NewsRepository
import com.khan.samacharapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SearchNews (
    private val newsRepository: NewsRepository
){
    operator fun invoke (searchQuery: String, sources:List<String>) : Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery =searchQuery , sources = sources)
    }
}