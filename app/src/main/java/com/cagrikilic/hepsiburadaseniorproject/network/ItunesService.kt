package com.cagrikilic


import com.cagrikilic.hepsiburadaseniorproject.network.model.Media
import com.cagrikilic.hepsiburadaseniorproject.network.model.ServiceResponse
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


enum class filter

private val BASE_URL = "https://itunes.apple.com/"

private val client = okhttp3.OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    })
    .addInterceptor(OkHttpProfilerInterceptor())
    .build()
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface ItunesServiceApi{
    @GET("search")
    suspend fun getMedia(
        @Query("term")
        term:String,
        @Query("media")
        media : String = "movie",
        @Query("offset")
        offset : Int,
        @Query("limit")
        limit : Int
    ) : ServiceResponse<Media>


}

object ItunesAppi{
    val retrofitService: ItunesServiceApi by lazy {
        retrofit.create(ItunesServiceApi::class.java)
    }
}


