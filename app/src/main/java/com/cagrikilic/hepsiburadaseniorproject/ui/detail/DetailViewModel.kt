package com.cagrikilic.hepsiburadaseniorproject.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagrikilic.ItunesAppi
import com.cagrikilic.hepsiburadaseniorproject.network.model.*
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel(mediaId: Int,mediaType:String) : ViewModel() {

    private val _selectedMusic = MutableLiveData<Music>()
    val selectedMusic: LiveData<Music>
        get() = _selectedMusic

    private val _selectedEbook = MutableLiveData<Ebook>()
    val selectedEbook: LiveData<Ebook>
        get() = _selectedEbook

    private val _selectedSoftware = MutableLiveData<Software>()
    val selectedSoftware: LiveData<Software>
        get() = _selectedSoftware

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie
    private val _image = MutableLiveData<String>()
    val image : LiveData<String>
        get() = _image

    init {
       when(mediaType){
           "feature-movie" -> getMovieDetails(mediaId)
           "song" ->  getMusicDetails(mediaId)
           "software" -> getSoftwareDetails(mediaId)
           "ebook" -> getEbookDetails(mediaId)
       }

    }

    private fun getMovieDetails(id:Int){
        viewModelScope.launch {
            try {
                val listResult = ItunesAppi.retrofitService.getMovieDetails(id)
                _selectedMovie.value = listResult.results?.get(0)
                _image.value = _selectedMovie.value!!.artworkUrl100!!
                Log.d("test", _selectedMovie.value.toString() )
            } catch (e: Exception) {
                Log.d("test",e.message.toString())
            }
        }

    }
    private fun getMusicDetails(id:Int){
        viewModelScope.launch {
            try {
                val listResult = ItunesAppi.retrofitService.getMusicDetails(id)
                _selectedMusic.value = listResult.results?.get(0)
                _image.value = _selectedMusic.value!!.artworkUrl100!!
                Log.d("test", _selectedMusic.value.toString() )
            } catch (e: Exception) {
                Log.d("test",e.message.toString())
            }
        }

    }
    private fun getSoftwareDetails(id:Int){
        viewModelScope.launch {
            try {
                val listResult = ItunesAppi.retrofitService.getSoftwareDetails(id)
                _selectedSoftware.value = listResult.results?.get(0)
                _image.value = _selectedSoftware.value!!.artworkUrl512!!
                Log.d("test", _selectedSoftware.value.toString() )
            } catch (e: Exception) {
                Log.d("test",e.message.toString())
            }
        }
    }
    private fun getEbookDetails(id:Int){
        viewModelScope.launch {
            try {
                val listResult = ItunesAppi.retrofitService.getEbookDetails(id)
                _selectedEbook.value = listResult.results?.get(0)
                _image.value = _selectedEbook.value!!.artworkUrl100!!
                Log.d("test", _selectedEbook.value.toString() )
            } catch (e: Exception) {
                Log.d("test",e.message.toString())
            }
        }
    }

    fun parseDate(releaseDate:String?) : String{
        val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val date: Date = inputFormat.parse(releaseDate)
        return outputFormat.format(date)
    }
}