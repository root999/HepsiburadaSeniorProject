package com.cagrikilic.hepsiburadaseniorproject.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.cagrikilic.ItunesAppi
import com.cagrikilic.hepsiburadaseniorproject.network.PAGE_SIZE
import com.cagrikilic.hepsiburadaseniorproject.network.SearchDataSource
import com.cagrikilic.hepsiburadaseniorproject.network.model.Media

interface  SearchRepository{
    suspend fun getMediaList(term:String,filter:String):LiveData<PagingData<Media>>
}

class SearchRepositoryImpl(
    private val service : ItunesAppi,
) : SearchRepository {
    override suspend fun getMediaList(term:String,filter:String): LiveData<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchDataSource(service,term,filter)
            }
        ).liveData
    }
}