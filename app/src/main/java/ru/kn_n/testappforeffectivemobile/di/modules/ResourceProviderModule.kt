package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Module
import dagger.Provides
import ru.kn_n.core.utils.ResourceProvider
import android.content.Context
import javax.inject.Singleton

@Module
class ResourceProviderModule {
    @Provides
    @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider = ResourceProvider(context)
}