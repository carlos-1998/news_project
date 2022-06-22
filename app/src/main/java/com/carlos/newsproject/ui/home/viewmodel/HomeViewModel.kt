package com.carlos.newsproject.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.newsproject.data.model.NewsResponse
import com.carlos.newsproject.data.repository.Repository
import com.carlos.newsproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {

    private val news = MutableLiveData<Resource<NewsResponse?>>()
    val responseNews: LiveData<Resource<NewsResponse?>> = news

    init {
        getNews()
    }

    private fun getNews() = viewModelScope.launch{
        news.postValue(Resource.loading(null))
        try {
            val data =  repository.getNews()
            if (data.isSuccessful){
                news.postValue(Resource.success(data.body()))
            }else{
                news.postValue(Resource.error(data.errorBody().toString(),null))
            }
        }catch (e:Exception){
            news.postValue(Resource.error("Algo salio mal",null))
        }
    }
}