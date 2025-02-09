package com.khan.samacharapp.presentation.details

import com.khan.samacharapp.domain.model.Article

sealed class DetailsEvent{

    data class UpsertDeleteArticle(val article: Article):DetailsEvent()

    object RemoveSideEffect:DetailsEvent()

}