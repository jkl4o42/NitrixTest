package com.jkl.nitrixtesttask.player.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.jkl.nitrixtesttask.databinding.FragmentVideoPlayerBinding
import com.jkl.nitrixtesttask.list.presentation.VideoUi
import com.jkl.nitrixtesttask.main.presentation.AbstractFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class VideoPlayerFragment : AbstractFragment<FragmentVideoPlayerBinding>() {

    private val viewModel: VideoPlayerViewModel by viewModel()
    private var exoPlayer: ExoPlayer? = null

    private val onPackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentVideoPlayerBinding {
        return FragmentVideoPlayerBinding.inflate(inflater, container, false)
    }

    @OptIn(UnstableApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exoPlayer = ExoPlayer.Builder(requireContext()).build().also { player ->
            binding.playerView.player = player
        }

        val videoUi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arguments?.getSerializable(KEY, VideoUi::class.java)
        else arguments?.getSerializable(KEY) as VideoUi

        viewModel.observe(viewLifecycleOwner) { mediaItems ->
            exoPlayer?.apply {
                setMediaItems(mediaItems)
                videoUi?.let { item ->
                    val initialMediaItem = item.mediaItem()
                    val index = mediaItems.indexOf(initialMediaItem)
                    seekTo(index, 0L)
                }
                prepare()
                play()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        activity?.onBackPressedDispatcher?.addCallback(onPackPressedCallback)
        exoPlayer?.play()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exoPlayer?.release()
        exoPlayer = null
    }

    companion object {
        private const val KEY = "video"
    }
}