package com.jkl.nitrixtesttask.list.domain

interface VideoListRepository {
    suspend fun fetch(): List<VideoDomain>
}