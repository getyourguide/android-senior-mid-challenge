package com.getyourguide.interview

import retrofit2.http.GET

interface ReviewsApi {

  @GET("activities/141648/reviews?offset=0&limit=60")
  suspend fun getReviews(): ReviewResponse

}
