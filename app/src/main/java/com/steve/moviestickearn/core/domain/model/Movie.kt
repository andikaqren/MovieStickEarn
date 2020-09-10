package com.steve.moviestickearn.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    var isFavorit: Boolean?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    var type: Int?,
    val vote_average: Double?,
    val vote_count: Int?
) : Parcelable {

}