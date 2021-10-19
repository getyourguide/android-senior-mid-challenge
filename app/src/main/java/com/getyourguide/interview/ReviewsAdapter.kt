package com.getyourguide.interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReviewsAdapter : RecyclerView.Adapter<ReviewsViewHolder>() {

  private val items: MutableList<ReviewResponse.ReviewsDto> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.row_reviews, parent, false)
    return ReviewsViewHolder(view)
  }

  override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount(): Int = items.size

  fun submitItems(items: List<ReviewResponse.ReviewsDto>) {
    this.items.clear()
    this.items.addAll(items)
    notifyDataSetChanged()
  }
}
