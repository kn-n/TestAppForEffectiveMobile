package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Module
import dagger.Provides
import ru.kn_n.store.presentation.model.FilterCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideFilterCache(): FilterCache = FilterCache()
}