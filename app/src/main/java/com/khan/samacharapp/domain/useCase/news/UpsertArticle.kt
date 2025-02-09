package com.khan.samacharapp.domain.useCase.news

import com.khan.samacharapp.data.local.NewsDao
import com.khan.samacharapp.domain.Repository.NewsRepository
import com.khan.samacharapp.domain.model.Article

class UpsertArticle (
    private val newsRepository: NewsRepository

){

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }




}