package com.khan.samacharapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.khan.samacharapp.data.Remote.NewsApi
import com.khan.samacharapp.data.Remote.NewsPagingSource
import com.khan.samacharapp.data.Remote.SearchNewsPagingSource
import com.khan.samacharapp.data.local.NewsDao
import com.khan.samacharapp.domain.Repository.NewsRepository
import com.khan.samacharapp.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl (
   private val newsApi: NewsApi,
   private val newsDao: NewsDao
):NewsRepository{
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config= PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources= sources.joinToString (separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config= PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery=searchQuery,
                    newsApi = newsApi,
                    sources= sources.joinToString (separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<Article>> {
       return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }

}