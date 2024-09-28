package com.jkl.nitrixtesttask.list.presentation

import android.net.Uri
import androidx.media3.common.MediaItem
import com.bumptech.glide.Glide
import com.jkl.nitrixtesttask.databinding.ItemVideoBinding
import java.io.Serializable

data class VideoUi(
    private val id: Int,
    private val description: String,
    private val sources: String,
    private val subtitle: String,
    private val thumb: String,
    private val title: String,
    private val duration: String
): Serializable {
    fun isTheSame(newItem: VideoUi): Boolean {
        return id == newItem.id
                || description == newItem.description
                || sources == newItem.sources
                || subtitle == newItem.subtitle
                || thumb == newItem.thumb
                || title == newItem.title
                || duration == newItem.duration
    }

    fun bind(binding: ItemVideoBinding) {
        binding.titleAppCompatTextView.text = this.title
        binding.durationAppCompatTextView.text = this.duration
        Glide.with(binding.appCompatImageView).load(thumb).into(binding.appCompatImageView)
    }

    fun mediaItem(): MediaItem {
      return MediaItem.fromUri(Uri.parse(sources))
    }
}