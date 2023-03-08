package com.getyourguide.interview

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewsApi {

  @GET("activities/141648/reviews?offset=0&limit=60")
  suspend fun getReviews(): ReviewResponse

  @GET("activities/141648/reviews?offset=0&limit=60")
  fun getReviewsRx(): Observable<ReviewResponse>
}
