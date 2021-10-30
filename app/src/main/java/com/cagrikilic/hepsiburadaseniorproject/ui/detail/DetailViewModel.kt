package com.cagrikilic.hepsiburadaseniorproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cagrikilic.hepsiburadaseniorproject.network.model.Media

class DetailViewModel(media: Media) : ViewModel() {
    private val _selectedMedia = MutableLiveData<Media>()

    val selectedMedia: LiveData<Media>
        get() = _selectedMedia

    init {
        _selectedMedia.value = media
    }
}