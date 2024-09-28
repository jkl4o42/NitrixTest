package com.jkl.nitrixtesttask.list.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jkl.nitrixtesttask.list.domain.VideoDomain
import com.jkl.nitrixtesttask.main.data.Mapper

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val sources: String,
    val subtitle: String,
    val thumb: String,
    val title: String,
    val duration: String
) : Mapper<VideoDomain> {

    override fun map(): VideoDomain {
        return VideoDomain(id, description, sources, subtitle, thumb, title, duration)
    }
}