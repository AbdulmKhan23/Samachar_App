package com.khan.samacharapp.domain.useCase.news

import com.khan.samacharapp.data.local.NewsDao
import com.khan.samacharapp.domain.Repository.NewsRepository
import com.khan.samacharapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository

){

     operator fun invoke(): Flow<List<Article>> {
       return newsRepository.getArticles()
    }




}