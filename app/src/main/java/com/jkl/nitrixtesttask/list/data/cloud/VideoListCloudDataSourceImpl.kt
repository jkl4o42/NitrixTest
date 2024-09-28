package com.jkl.nitrixtesttask.list.data.cloud

import com.jkl.nitrixtesttask.list.data.cloud.model.VideoCloud

class VideoListCloudDataSourceImpl(
    private val videoApiService: VideoApiService
) : VideoListCloudDataSource {
    override suspend fun fetch(): List<VideoCloud> {
        return try {
            videoApiService.getVideos()
        } catch (e: Exception) {
            emptyList()
        }
    }
}