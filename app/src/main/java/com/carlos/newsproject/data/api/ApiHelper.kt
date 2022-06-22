package com.carlos.newsproject.data.api

import com.carlos.newsproject.data.model.NewsResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getNews():Response<NewsResponse>
}