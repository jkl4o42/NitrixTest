package com.jkl.nitrixtesttask.list.di

import com.jkl.nitrixtesttask.list.data.VideoListRepositoryImpl
import com.jkl.nitrixtesttask.list.data.cache.AppDatabase
import com.jkl.nitrixtesttask.list.data.cache.VideoDao
import com.jkl.nitrixtesttask.list.data.cache.VideoListCacheDataSource
import com.jkl.nitrixtesttask.list.data.cache.VideoListCacheDataSourceImpl
import com.jkl.nitrixtesttask.list.data.cloud.RetrofitClient
import com.jkl.nitrixtesttask.list.data.cloud.VideoApiService
import com.jkl.nitrixtesttask.list.data.cloud.VideoListCloudDataSource
import com.jkl.nitrixtesttask.list.data.cloud.VideoListCloudDataSourceImpl
import com.jkl.nitrixtesttask.list.domain.VideoListInteractor
import com.jkl.nitrixtesttask.list.domain.VideoListInteractorImpl
import com.jkl.nitrixtesttask.list.domain.VideoListRepository
import com.jkl.nitrixtesttask.list.presentation.VideoListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val listModule = module {

    single<VideoListInteractor> { VideoListInteractorImpl(get()) }
    single<VideoListRepository> { VideoListRepositoryImpl(get(), get()) }
    single<VideoListCacheDataSource> { VideoListCacheDataSourceImpl(get()) }
    single<VideoListCloudDataSource> { VideoListCloudDataSourceImpl(get()) }
    single<VideoListCloudDataSource> { VideoListCloudDataSourceImpl(get()) }
    single<VideoApiService> { RetrofitClient.instance.create(VideoApiService::class.java) }
    single<VideoDao> { AppDatabase.getDatabase(get()).videoDao() }

    viewModel { VideoListViewModel(get()) }
}