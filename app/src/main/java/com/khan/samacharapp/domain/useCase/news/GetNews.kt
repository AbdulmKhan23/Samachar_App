package com.khan.samacharapp.domain.useCase.news

import androidx.paging.PagingData
import com.khan.samacharapp.domain.Repository.NewsRepository
import com.khan.samacharapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke (sources:List<String>) :Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}