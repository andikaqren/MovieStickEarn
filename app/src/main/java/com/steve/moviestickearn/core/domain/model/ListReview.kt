package com.steve.moviestickearn.core.domain.model


data class ListReview(
    val id: Int,
    val page: Int,
    val total_pages: Int,
    val total_result: Int,
    val results: List<Review>
)