package com.jkl.nitrixtesttask.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.jkl.nitrixtesttask.databinding.FragmentVideoListBinding
import com.jkl.nitrixtesttask.main.presentation.AbstractFragment
import com.jkl.nitrixtesttask.player.presentation.VideoPlayerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoListFragment : AbstractFragment<FragmentVideoListBinding>() {

    private val viewModel: VideoListViewModel by viewModel()
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentVideoListBinding =
        FragmentVideoListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(viewLifecycleOwner) { uiState ->
            uiState.bind(
                binding = binding,
                onRetry = { viewModel.fetch() },
                onVideoSelected = { video ->
                    navigateTo(
                        VideoPlayerFragment::class.java,
                        Bundle().apply { putSerializable(KEY, video) }
                    )
                })
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)
    }

    companion object {
        private const val KEY = "video"
    }
}