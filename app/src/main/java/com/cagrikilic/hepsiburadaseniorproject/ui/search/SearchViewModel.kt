package com.cagrikilic.hepsiburadaseniorproject

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cagrikilic.ItunesAppi
import com.cagrikilic.hepsiburadaseniorproject.network.model.Media
import com.cagrikilic.hepsiburadaseniorproject.repo.SearchRepository
import kotlinx.coroutines.launch

enum class DataStatus { LOADING, ERROR, DONE }

class SearchViewModel(private val repository: SearchRepository) : ViewModel(){


    private val _medias = MutableLiveData<PagingData<Media>>()
    val medias : LiveData<PagingData<Media>>
        get() = _medias
    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response
    private val _status = MutableLiveData<DataStatus>()

    val status: LiveData<DataStatus>
        get() = _status

    private val _navigateToSelectedMedia = MutableLiveData<Media>()
    val navigateToSelectedMedia: MutableLiveData<Media>
        get() = _navigateToSelectedMedia


    fun displayMediaDetails(media: Media) {
        _navigateToSelectedMedia.value = media
    }

    fun displayMediaDetailsCompleted() {
        _navigateToSelectedMedia.value = null
    }

//    private fun getMusics(term:String) {
//        viewModelScope.launch {
//            try {
//                val listResult = ItunesAppi.retrofitService.getMusics(term)
//                _movies.value = listResult.results
//                _response.value = "Failure: ${_movies.value!!.size}"
//            } catch (e: Exception) {
//                _response.value = "Failure: ${e.message}"
//            }
//        }
//    }
    suspend fun getMediaList(term: String = "all",filter:String): LiveData<PagingData<Media>> {
        val response = repository.getMediaList(term,filter).cachedIn(viewModelScope)
        _medias.value = response.value
        return response
    }




}
