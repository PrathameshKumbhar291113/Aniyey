package com.prathameshkumbhar.aniyey.connection

import android.content.Context
import com.prathameshkumbhar.aniyey.service.NetworkMonitorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitorService {
        return NetworkMonitorService(context)
    }
}