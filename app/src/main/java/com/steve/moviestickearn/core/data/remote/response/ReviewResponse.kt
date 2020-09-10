package com.steve.moviestickearn.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.steve.moviestickearn.core.domain.model.Review
import kotlinx.android.parcel.Parcelize


data class ReviewResponse(
    @field:SerializedName("results")
    val results: List<Review>

)