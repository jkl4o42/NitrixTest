package com.jkl.nitrixtesttask.list.data.cloud

import com.jkl.nitrixtesttask.list.data.cloud.model.VideoCloud
import retrofit2.http.GET

interface VideoApiService {

    @GET("jkl4o42/84dff673f028427e518439ac3fbf5274/raw/905481abba407e1564666ef4f7239d297a177049/video.json")
    suspend fun getVideos(): List<VideoCloud>
}