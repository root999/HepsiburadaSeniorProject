package com.cagrikilic.hepsiburadaseniorproject.network.model


import com.squareup.moshi.Json

data class Ebook(
    @Json(name = "artistId")
    var artistId: Int?,
    @Json(name = "artistIds")
    var artistIds: List<Int>?,
    @Json(name = "artistName")
    var artistName: String?,
    @Json(name = "artistViewUrl")
    var artistViewUrl: String?,
    @Json(name = "artworkUrl100")
    var artworkUrl100: String?,
    @Json(name = "artworkUrl60")
    var artworkUrl60: String?,
    @Json(name = "averageUserRating")
    var averageUserRating: Double?,
    @Json(name = "currency")
    var currency: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "fileSizeBytes")
    var fileSizeBytes: Int?,
    @Json(name = "formattedPrice")
    var formattedPrice: String?,
    @Json(name = "genreIds")
    var genreIds: List<String>?,
    @Json(name = "genres")
    var genres: List<String>?,
    @Json(name = "kind")
    var kind: String?,
    @Json(name = "price")
    var price: Double?,
    @Json(name = "releaseDate")
    var releaseDate: String?,
    @Json(name = "trackCensoredName")
    var trackCensoredName: String?,
    @Json(name = "trackId")
    var trackId: Int?,
    @Json(name = "trackName")
    var trackName: String?,
    @Json(name = "trackViewUrl")
    var trackViewUrl: String?,
    @Json(name = "userRatingCount")
    var userRatingCount: Int?
)