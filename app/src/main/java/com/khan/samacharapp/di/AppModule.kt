package com.khan.samacharapp.di

import android.app.Application
import androidx.room.Room
import com.khan.samacharapp.data.Remote.NewsApi
import com.khan.samacharapp.data.local.NewsDao
import com.khan.samacharapp.data.local.NewsDatabase
import com.khan.samacharapp.data.local.NewsTypeConverter
import com.khan.samacharapp.data.manager.LocalUserManagerImpl
import com.khan.samacharapp.data.repository.NewsRepositoryImpl
import com.khan.samacharapp.domain.Repository.NewsRepository
import com.khan.samacharapp.domain.manager.LocalUserManager
import com.khan.samacharapp.domain.useCase.app_entry.AppEntryUsesCases
import com.khan.samacharapp.domain.useCase.app_entry.SaveAppEntry
import com.khan.samacharapp.domain.useCase.app_entry.readAppEntry
import com.khan.samacharapp.domain.useCase.news.DeleteArticle
import com.khan.samacharapp.domain.useCase.news.GetNews
import com.khan.samacharapp.domain.useCase.news.NewsUseCases
import com.khan.samacharapp.domain.useCase.news.SearchNews
import com.khan.samacharapp.domain.useCase.news.SelectArticle
import com.khan.samacharapp.domain.useCase.news.SelectArticles
import com.khan.samacharapp.domain.useCase.news.UpsertArticle
import com.khan.samacharapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(
        application
    )
    @Provides
    @Singleton
    fun provideAppEntryUsesCases(
        localUserManager: LocalUserManager
    )= AppEntryUsesCases(
        readAppEntry = readAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository = NewsRepositoryImpl(newsApi,newsDao)


    @Provides
    @Singleton
    fun provideNewsUsesCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )

    }


    @Provides
    @Singleton
    fun newsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "NEWS_DATABASE_NAME"
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) = newsDatabase.newsDao



}