package com.prathameshkumbhar.aniyey.features.anime_list.domain.usecase

import androidx.paging.PagingData
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.features.anime_list.domain.repository.AnimeListRepository
import com.prathameshkumbhar.aniyey.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val animeListRepository: AnimeListRepository
) {
    operator fun invoke(): Flow<PagingData<GetAnimeListResponse.Data>> {
        return animeListRepository.getAnimeList()
    }
}