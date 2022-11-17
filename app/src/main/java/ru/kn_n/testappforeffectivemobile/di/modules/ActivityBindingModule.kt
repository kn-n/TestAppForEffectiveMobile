package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kn_n.testappforeffectivemobile.di.scopes.ActivityScoped
import ru.kn_n.testappforeffectivemobile.presentation.MainActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            FragmentModule::class
        ])
    abstract fun providesMainActivity(): MainActivity
}