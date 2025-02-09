package com.khan.samacharapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khan.samacharapp.domain.model.Article

@Database(entities = [Article::class], version = 2, exportSchema = false)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase :RoomDatabase() {

    abstract val newsDao: NewsDao


}