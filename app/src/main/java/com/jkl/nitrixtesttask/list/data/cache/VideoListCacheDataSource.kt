package com.jkl.nitrixtesttask.list.data.cache

import com.jkl.nitrixtesttask.list.data.cache.model.VideoEntity

interface VideoListCacheDataSource {

    suspend fun all(): List<VideoEntity>
    suspend fun searchByTitle(value: String): List<VideoEntity>
    suspend fun insertAll(list: List<VideoEntity>)
}