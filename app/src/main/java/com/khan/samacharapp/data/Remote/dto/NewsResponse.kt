package com.khan.samacharapp.data.Remote.dto


import com.google.gson.annotations.SerializedName
import com.khan.samacharapp.domain.model.Article

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)