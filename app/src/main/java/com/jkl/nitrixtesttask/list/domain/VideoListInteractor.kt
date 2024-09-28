package com.jkl.nitrixtesttask.list.domain

import com.jkl.nitrixtesttask.list.presentation.VideoUi

interface VideoListInteractor {
    suspend fun fetch(): List<VideoUi>
}