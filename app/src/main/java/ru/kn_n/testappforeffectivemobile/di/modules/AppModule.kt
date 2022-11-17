package ru.kn_n.testappforeffectivemobile.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        NavigationModule::class,
        ViewModelModule::class,
        RepositoriesModule::class,
        ResourceProviderModule::class,
        CacheModule::class
    ]
)
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}