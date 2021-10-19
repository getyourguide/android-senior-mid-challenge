package com.getyourguide.interview

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewsApi {

  @GET("activities/23776/reviews")
  suspend fun getReviews(): ReviewResponse

  @GET("activities/23776/reviews")
  fun getReviewsRx(): Observable<ReviewResponse>
}
