package com.getyourguide.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.getyourguide.interview.databinding.FragmentReviewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewsFragment : Fragment() {

  companion object {
    fun newInstance(): Fragment = ReviewsFragment()
  }

  private var _binding: FragmentReviewsBinding? = null
  private val binding: FragmentReviewsBinding
    get() = _binding!!

  private val api: ReviewsApi = RetrofitBuilder.build(ReviewsApi::class.java)
  private val adapter = ReviewsAdapter()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentReviewsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.recyclerView.adapter = adapter

    loadData()
  }

  private fun loadData() {
    GlobalScope.launch {
      flow {
        emit(api.getReviews())
      }.collect {
        withContext(Dispatchers.Main) {
          onSuccess(it.reviews)
        }
      }
    }
  }

  private fun onSuccess(reviews: List<ReviewResponse.ReviewsDto>) {
    adapter.submitItems(reviews)
    binding.progress.visibility = View.GONE

  }
}
