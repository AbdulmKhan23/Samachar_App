package com.khan.samacharapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.khan.samacharapp.domain.useCase.news.GetNews
import com.khan.samacharapp.domain.useCase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
):ViewModel(){

    val news = newsUseCases.getNews(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)



}