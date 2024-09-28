package com.jkl.nitrixtesttask.player.di

import com.jkl.nitrixtesttask.player.presentation.VideoPlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val videoPlayerModule = module {

    viewModel { VideoPlayerViewModel(get()) }
}