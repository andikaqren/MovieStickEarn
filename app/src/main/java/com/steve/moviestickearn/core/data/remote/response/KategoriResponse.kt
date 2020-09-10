package com.steve.moviestickearn.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class KategoriResponse(
    @field:SerializedName("genres")
    var genres: List<GenreResponse>
)