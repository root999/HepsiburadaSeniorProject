package com.cagrikilic.hepsiburadaseniorproject.network.model

import com.squareup.moshi.Json

data class ServiceResponse<T>(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results : List<T>
)