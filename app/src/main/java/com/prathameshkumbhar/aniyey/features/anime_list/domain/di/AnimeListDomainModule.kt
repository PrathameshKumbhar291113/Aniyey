package com.prathameshkumbhar.aniyey.features.anime_list.domain.di

import com.prathameshkumbhar.aniyey.features.anime_list.domain.repository.AnimeListRepository
import com.prathameshkumbhar.aniyey.features.anime_list.domain.usecase.GetAnimeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeListDomainModule {

    @Singleton
    @Provides
    fun providesGetAnimeListUseCase(animeListRepository: AnimeListRepository): GetAnimeListUseCase {
        return GetAnimeListUseCase(animeListRepository)
    }

}