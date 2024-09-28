package com.jkl.nitrixtesttask.player.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import com.jkl.nitrixtesttask.list.domain.VideoListInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoPlayerViewModel(
    private val videoListInteractor: VideoListInteractor
) : ViewModel() {

    private val mediaItemsLiveData: MutableLiveData<List<MediaItem>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = videoListInteractor.fetch()
            val mediaItems = list.map { it.mediaItem() }
            mediaItemsLiveData.postValue(mediaItems)
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<MediaItem>>) =
        mediaItemsLiveData.observe(owner, observer)
}