package com.carlos.newsproject.ui.ryckmorty.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.newsproject.data.model.RickMortyResponse
import com.carlos.newsproject.data.repository.Repository
import com.carlos.newsproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val characters = MutableLiveData<Resource<RickMortyResponse?>>()
    val responseCharacters : LiveData<Resource<RickMortyResponse?>> = characters

    init {
        getCharacter()
    }

    private fun getCharacter() = viewModelScope.launch {
        characters.postValue(Resource.loading(null))
        try {
            val data = repository.getCharacters()
            if (data.isSuccessful){
                characters.postValue(Resource.success(data.body()))
            }else{
                characters.postValue(Resource.error(data.errorBody().toString(),null))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}