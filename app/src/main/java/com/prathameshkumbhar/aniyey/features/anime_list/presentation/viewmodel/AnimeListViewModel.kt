package com.prathameshkumbhar.aniyey.features.anime_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.features.anime_list.domain.usecase.GetAnimeListUseCase
import com.prathameshkumbhar.aniyey.service.NetworkMonitorService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitorService,
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    val isNetworkAvailable: LiveData<Boolean> = networkMonitor.isNetworkAvailable

    val animeList: Flow<PagingData<GetAnimeListResponse.Data>> = getAnimeListUseCase()
        .cachedIn(viewModelScope)
}