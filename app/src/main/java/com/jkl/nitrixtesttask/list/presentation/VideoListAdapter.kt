package com.jkl.nitrixtesttask.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jkl.nitrixtesttask.databinding.ItemVideoBinding

class VideoListAdapter(
    private val onVideoSelected: (VideoUi) -> Unit
) : ListAdapter<VideoUi, VideoListAdapter.ViewHolder>(VideoUiDiff()) {


    class ViewHolder(
        private val binding: ItemVideoBinding,
        private val onVideoSelected: (VideoUi) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoUi) {
            item.bind(binding)
            binding.root.setOnClickListener {
                onVideoSelected.invoke(item)
            }
        }
    }

    class VideoUiDiff : DiffUtil.ItemCallback<VideoUi>() {
        override fun areItemsTheSame(oldItem: VideoUi, newItem: VideoUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VideoUi, newItem: VideoUi): Boolean {
            return oldItem.isTheSame(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onVideoSelected)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}