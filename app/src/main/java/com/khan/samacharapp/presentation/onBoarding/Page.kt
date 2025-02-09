package com.khan.samacharapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.khan.samacharapp.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)
val pages = listOf(
    Page(
        title = "Stay Informed",
        description = "Get the latest news updates from around the world, all in one place.",
        image = R.drawable.onboarding4
    ),
    Page(
        title = "Explore Various Categories",
        description = "Discover news in different categories like politics, sports, technology, and entertainment.",
        image = R.drawable.onboarding5
    ),
    Page(
        title = "Save & Read Later",
        description = "Easily bookmark your favorite articles and read them at your convenience.",
        image = R.drawable.onboarding6
    )
)

