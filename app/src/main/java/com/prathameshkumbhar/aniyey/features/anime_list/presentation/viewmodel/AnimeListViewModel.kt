package com.prathameshkumbhar.aniyey.features.anime_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.prathameshkumbhar.aniyey.features.anime_list.domain.usecase.GetAnimeListUseCase
import com.prathameshkumbhar.aniyey.service.NetworkMonitorService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitorService,
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    val isNetworkAvailable: LiveData<Boolean> = networkMonitor.isNetworkAvailable

    private val sortDescending = MutableStateFlow(false)

    fun toggleSortOrder() {
        sortDescending.value = !sortDescending.value
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val animeList = sortDescending
        .flatMapLatest { sortDescending ->
            getAnimeListUseCase(sortDescending)
        }
        .cachedIn(viewModelScope)
}