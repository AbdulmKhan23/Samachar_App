package com.khan.samacharapp.domain.useCase.news

data class NewsUseCases(
    val getNews:GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle
)
