package com.jkl.nitrixtesttask.list.data

import com.jkl.nitrixtesttask.list.data.cache.VideoListCacheDataSource
import com.jkl.nitrixtesttask.list.data.cloud.VideoListCloudDataSource
import com.jkl.nitrixtesttask.list.domain.VideoDomain
import com.jkl.nitrixtesttask.list.domain.VideoListRepository

class VideoListRepositoryImpl(
    private val videoListCacheDataSource: VideoListCacheDataSource,
    private val videoListCloudDataSource: VideoListCloudDataSource
) : VideoListRepository {
    override suspend fun fetch(): List<VideoDomain> {
        val cache = videoListCacheDataSource.all()
        if (cache.isEmpty()) {
            val cloud = videoListCloudDataSource.fetch()
            if (cloud.isEmpty()) return emptyList()
            val mappedToEntity = cloud.mapIndexed { index, videoCloud -> videoCloud.mapToEntity(index) }
            videoListCacheDataSource.insertAll(mappedToEntity)
            return mappedToEntity.map { it.map() }
        }
        val cloud = videoListCloudDataSource.fetch()
        val mappedToEntity = cloud.mapIndexed { index, videoCloud -> videoCloud.mapToEntity(index) }
        if (cloud.isEmpty() || cache == mappedToEntity) return cache.map { it.map() }
        videoListCacheDataSource.insertAll(mappedToEntity)
        return mappedToEntity.map { it.map() }
    }
}