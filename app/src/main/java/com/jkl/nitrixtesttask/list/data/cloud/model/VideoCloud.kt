package com.jkl.nitrixtesttask.list.data.cloud.model

import com.jkl.nitrixtesttask.list.data.cache.model.VideoEntity

data class VideoCloud(
    private val description: String,
    private val sources: String,
    private val subtitle: String,
    private val thumb: String,
    private val title: String,
    private val duration: String
) {

    fun mapToEntity(id: Int): VideoEntity {
        return VideoEntity(id, description, sources, subtitle, thumb, title, duration)
    }
}