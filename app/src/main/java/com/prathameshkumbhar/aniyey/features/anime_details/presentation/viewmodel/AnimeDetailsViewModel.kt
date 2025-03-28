package com.prathameshkumbhar.aniyey.features.anime_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import com.prathameshkumbhar.aniyey.features.anime_details.domain.usecase.GetAnimeDetailsUseCase
import com.prathameshkumbhar.aniyey.service.NetworkMonitorService
import com.prathameshkumbhar.aniyey.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitorService,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
) : ViewModel() {
    private val _animeId = MutableLiveData<Int?>()
    val animeId: LiveData<Int?> get() = _animeId

    val isNetworkAvailable: LiveData<Boolean> = networkMonitor.isNetworkAvailable
    private val _animeDetails =
        MutableLiveData<NetworkResult<Response<GetAnimeDetailsByIdResponse>>?>()
    val animeDetails: LiveData<NetworkResult<Response<GetAnimeDetailsByIdResponse>>?> get() = _animeDetails


    fun setAnimeId(animeId: Int) {
        _animeId.value = animeId
    }

    fun getAnimeDetailsById(animeId: Int) {
        getAnimeDetailsUseCase(animeId).onEach {
            _animeDetails.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun clearAnimeDetails(){
        _animeDetails.postValue(null)
    }


}