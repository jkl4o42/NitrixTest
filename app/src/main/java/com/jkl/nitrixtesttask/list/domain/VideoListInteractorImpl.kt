package com.jkl.nitrixtesttask.list.domain

import com.jkl.nitrixtesttask.list.presentation.VideoUi

class VideoListInteractorImpl(
    private val videoListRepository: VideoListRepository
) : VideoListInteractor {
    override suspend fun fetch(): List<VideoUi> = videoListRepository.fetch().map { it.map() }
}