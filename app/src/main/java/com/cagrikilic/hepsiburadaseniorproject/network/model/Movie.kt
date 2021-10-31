package com.cagrikilic.hepsiburadaseniorproject.network.model


import com.squareup.moshi.Json

data class Movie(
    @Json(name = "artistName")
    var artistName: String?,
    @Json(name = "artworkUrl100")
    var artworkUrl100: String?,
    @Json(name = "artworkUrl30")
    var artworkUrl30: String?,
    @Json(name = "artworkUrl60")
    var artworkUrl60: String?,
    @Json(name = "collectionExplicitness")
    var collectionExplicitness: String?,
    @Json(name = "collectionHdPrice")
    var collectionHdPrice: Double?,
    @Json(name = "collectionPrice")
    var collectionPrice: Double?,
    @Json(name = "contentAdvisoryRating")
    var contentAdvisoryRating: String?,
    @Json(name = "country")
    var country: String?,
    @Json(name = "currency")
    var currency: String?,
    @Json(name = "kind")
    var kind: String?,
    @Json(name = "longDescription")
    var longDescription: String?,
    @Json(name = "previewUrl")
    var previewUrl: String?,
    @Json(name = "primaryGenreName")
    var primaryGenreName: String?,
    @Json(name = "releaseDate")
    var releaseDate: String?,
    @Json(name = "trackCensoredName")
    var trackCensoredName: String?,
    @Json(name = "trackExplicitness")
    var trackExplicitness: String?,
    @Json(name = "trackHdPrice")
    var trackHdPrice: Double?,
    @Json(name = "trackHdRentalPrice")
    var trackHdRentalPrice: Double?,
    @Json(name = "trackId")
    var trackId: Int?,
    @Json(name = "trackName")
    var trackName: String?,
    @Json(name = "trackPrice")
    var trackPrice: Double?,
    @Json(name = "trackRentalPrice")
    var trackRentalPrice: Double?,
    @Json(name = "trackTimeMillis")
    var trackTimeMillis: Int?,
    @Json(name = "trackViewUrl")
    var trackViewUrl: String?,
    @Json(name = "wrapperType")
    var wrapperType: String?
)