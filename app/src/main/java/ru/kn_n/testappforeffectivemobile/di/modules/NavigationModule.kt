package ru.kn_n.testappforeffectivemobile.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter():Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder():NavigatorHolder = cicerone.getNavigatorHolder()

}