package com.jkl.nitrixtesttask.list.domain

import com.jkl.nitrixtesttask.list.presentation.VideoUi
import com.jkl.nitrixtesttask.main.data.Mapper

data class VideoDomain(
    private val id: Int,
    private val description: String,
    private val sources: String,
    private val subtitle: String,
    private val thumb: String,
    private val title: String,
    private val duration: String
) : Mapper<VideoUi> {
    override fun map(): VideoUi {
        return VideoUi(id, description, sources, subtitle, thumb, title, duration)
    }
}