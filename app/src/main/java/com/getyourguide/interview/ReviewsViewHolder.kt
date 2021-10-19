package com.getyourguide.interview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.getyourguide.interview.databinding.RowReviewsBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReviewsViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

  private val binding = RowReviewsBinding.bind(containerView)


  fun bind(item: ReviewResponse.ReviewsDto) {

    binding.dateText.text = LocalDateTime.parse(item.created, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz"))
      .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    binding.ratingBar.rating = item.rating ?: 0f
    binding.message.text = item.message
    binding.author.text = item.author.fullName + "-" + item.author.country
    binding.image.load(item.author.photo) {
      fallback(R.drawable.ic_launcher_background)
      crossfade(true)
    }
  }
}
