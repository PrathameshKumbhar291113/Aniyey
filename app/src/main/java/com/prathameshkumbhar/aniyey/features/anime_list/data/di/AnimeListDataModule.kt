package com.prathameshkumbhar.aniyey.features.anime_list.data.di

import com.prathameshkumbhar.aniyey.connection.ApiCommunicator
import com.prathameshkumbhar.aniyey.features.anime_list.data.repository.AnimeListRepoImpl
import com.prathameshkumbhar.aniyey.features.anime_list.domain.repository.AnimeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeListDataModule {
    @Singleton
    @Provides
    fun providesAnimeListRepository(
        apiCommunicator: ApiCommunicator
    ): AnimeListRepository {
        return AnimeListRepoImpl(apiCommunicator)
    }
}