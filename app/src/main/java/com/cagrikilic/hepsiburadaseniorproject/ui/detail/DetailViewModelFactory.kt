package com.cagrikilic.hepsiburadaseniorproject.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cagrikilic.hepsiburadaseniorproject.network.model.Media

class DetailViewModelFactory(
    private val mediaId: Int,
    private val mediaType : String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mediaId,mediaType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
