package com.carlos.newsproject.data.api

import com.carlos.newsproject.data.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject


class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper {

    override suspend fun getNews(): Response<NewsResponse>  = apiService.getNews()
}