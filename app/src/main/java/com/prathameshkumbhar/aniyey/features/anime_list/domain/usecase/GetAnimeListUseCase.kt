package com.prathameshkumbhar.aniyey.features.anime_list.domain.usecase

import androidx.paging.PagingData
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.features.anime_list.domain.repository.AnimeListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val animeListRepository: AnimeListRepository
) {
    operator fun invoke(sortDescending: Boolean): Flow<PagingData<GetAnimeListResponse.Data>> {
        return animeListRepository.getAnimeList(sortDescending)
    }
}