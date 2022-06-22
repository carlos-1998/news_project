package com.carlos.newsproject.data.repository

import com.carlos.newsproject.data.api.ApiHelper
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getNews() = apiHelper.getNews()
}