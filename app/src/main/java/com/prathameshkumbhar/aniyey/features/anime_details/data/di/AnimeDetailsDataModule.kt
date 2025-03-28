package com.prathameshkumbhar.aniyey.features.anime_details.data.di


import com.prathameshkumbhar.aniyey.connection.ApiCommunicator
import com.prathameshkumbhar.aniyey.features.anime_details.data.repository.AnimeDetailsRepoImpl
import com.prathameshkumbhar.aniyey.features.anime_details.domain.repository.AnimeDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeDetailsDataModule {
    @Singleton
    @Provides
    fun providesAnimeDetailsRepository(
        apiCommunicator: ApiCommunicator
    ): AnimeDetailsRepository {
        return AnimeDetailsRepoImpl(apiCommunicator)
    }
}