package com.prathameshkumbhar.aniyey.features.anime_details.domain.di

import com.prathameshkumbhar.aniyey.features.anime_details.domain.repository.AnimeDetailsRepository
import com.prathameshkumbhar.aniyey.features.anime_details.domain.usecase.GetAnimeDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeDetailsDomainModule {

    @Singleton
    @Provides
    fun providesGetAnimeDetailsUseCase(animeDetailsRepository: AnimeDetailsRepository): GetAnimeDetailsUseCase {
        return GetAnimeDetailsUseCase(animeDetailsRepository)
    }

}