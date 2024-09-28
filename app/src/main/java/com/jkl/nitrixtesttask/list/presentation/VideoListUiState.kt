package com.jkl.nitrixtesttask.list.presentation

import android.view.View
import com.jkl.nitrixtesttask.databinding.FragmentVideoListBinding

interface VideoListUiState {

    fun bind(binding: FragmentVideoListBinding, onRetry: () -> Unit, onVideoSelected: (VideoUi) -> Unit)

    data class Error(private val message: String) : VideoListUiState {
        override fun bind(binding: FragmentVideoListBinding, onRetry: () -> Unit, onVideoSelected: (VideoUi) -> Unit) {
            binding.videoRecyclerView.visibility = View.GONE
            binding.errorLinearLayoutCompat.visibility = View.VISIBLE
            binding.errorMessage.text = message
            binding.retryButton.setOnClickListener {
                binding.errorLinearLayoutCompat.visibility = View.GONE
                onRetry.invoke()
            }
        }
    }

    data class Show(private val list: List<VideoUi>) : VideoListUiState {

        override fun bind(binding: FragmentVideoListBinding, onRetry: () -> Unit, onVideoSelected: (VideoUi) -> Unit) {
            val adapter = VideoListAdapter(onVideoSelected)
            binding.videoRecyclerView.visibility = View.VISIBLE
            binding.errorLinearLayoutCompat.visibility = View.GONE
            binding.videoRecyclerView.adapter = adapter
            adapter.submitList(list)
        }
    }
}