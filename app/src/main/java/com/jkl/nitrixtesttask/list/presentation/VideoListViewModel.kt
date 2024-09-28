package com.jkl.nitrixtesttask.list.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkl.nitrixtesttask.list.domain.VideoListInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoListViewModel(
    private val videoListInteractor: VideoListInteractor
) : ViewModel() {

    init { fetch() }

    private val uiState: MutableLiveData<VideoListUiState> = MutableLiveData()

    fun observe(owner: LifecycleOwner, observer: Observer<VideoListUiState>) =
        uiState.observe(owner, observer)

    fun fetch() = viewModelScope.launch(Dispatchers.IO) {
        val list = videoListInteractor.fetch()
        val state = if (list.isEmpty()) VideoListUiState.Error("Error, cannot fetch data.")
        else VideoListUiState.Show(list)
        uiState.postValue(state)
    }
}