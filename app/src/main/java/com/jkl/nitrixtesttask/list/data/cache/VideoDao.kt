package com.jkl.nitrixtesttask.list.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jkl.nitrixtesttask.list.data.cache.model.VideoEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos: List<VideoEntity>)

    @Query("SELECT * FROM video")
    suspend fun getAllVideos(): List<VideoEntity>

    @Query("SELECT * FROM video WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%'")
    suspend fun searchVideosByTitle(query: String): List<VideoEntity>

}