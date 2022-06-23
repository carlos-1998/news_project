package com.carlos.newsproject.data.api

import com.carlos.newsproject.data.model.NewsResponse
import com.carlos.newsproject.data.model.RickMortyResponse
import retrofit2.Response
import javax.inject.Inject


class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper {

    override suspend fun getNews(): Response<NewsResponse>  = apiService.getNews()

    override suspend fun getCharacters(): Response<RickMortyResponse> = apiService.getCharacters()

}