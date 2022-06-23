package com.carlos.newsproject.data.api

import com.carlos.newsproject.data.model.NewsResponse
import com.carlos.newsproject.data.model.RickMortyResponse
import com.carlos.newsproject.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("v2/everything?q=tesla&from=2022-05-23&sortBy=publishedAt&apiKey=${Constants.API_KEY}")
    suspend fun getNews():Response<NewsResponse>


    @Headers("Content-Type: application/json")
    @GET("https://rickandmortyapi.com/api/character")
    suspend fun getCharacters():Response<RickMortyResponse>
}