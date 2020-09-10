package com.steve.moviestickearn.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val author: String?,
    val content: String?,
    val id: String,
    val url: String?
) : Parcelable {

}