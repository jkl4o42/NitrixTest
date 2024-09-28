package com.jkl.nitrixtesttask.list.data.cache

import com.jkl.nitrixtesttask.list.data.cache.model.VideoEntity

class VideoListCacheDataSourceImpl(
    private val videoDao: VideoDao
) : VideoListCacheDataSource {

    override suspend fun all(): List<VideoEntity> = videoDao.getAllVideos()
    override suspend fun searchByTitle(value: String): List<VideoEntity> = videoDao.searchVideosByTitle(value)
    override suspend fun insertAll(list: List<VideoEntity>) = videoDao.insertAll(list)
}