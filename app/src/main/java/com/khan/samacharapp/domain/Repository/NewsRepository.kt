package com.khan.samacharapp.domain.Repository

import androidx.paging.PagingData
import com.khan.samacharapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>


    fun searchNews(searchQuery:String ,sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun selectArticle(url:String):Article?

}