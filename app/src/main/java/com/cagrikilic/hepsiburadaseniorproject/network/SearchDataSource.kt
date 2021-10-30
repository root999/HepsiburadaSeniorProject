package com.cagrikilic.hepsiburadaseniorproject.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cagrikilic.ItunesAppi
import com.cagrikilic.hepsiburadaseniorproject.network.model.Media
import java.lang.Exception

const val PAGE_SIZE = 20
private const val LOAD_SIZE = 0
class SearchDataSource(
    private val service : ItunesAppi,
    private val query : String,
    private val filter :String
    ) : PagingSource<Int,Media>() {


    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
      return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val position = params.key ?: LOAD_SIZE
        val offset = if(params.key != null)((position) * PAGE_SIZE) + 1 else LOAD_SIZE
        return try{
            val response = service.retrofitService.getMedia(query,offset = offset,limit=params.loadSize,media = filter).results
            val nextKey = if (response.isEmpty()){
                null
            }
            else{
                position + params.loadSize/ PAGE_SIZE
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }

    }

}