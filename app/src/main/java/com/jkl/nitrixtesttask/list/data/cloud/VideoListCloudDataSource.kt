package com.jkl.nitrixtesttask.list.data.cloud

import com.jkl.nitrixtesttask.list.data.cloud.model.VideoCloud

interface VideoListCloudDataSource {

    suspend fun fetch(): List<VideoCloud>
}