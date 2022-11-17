package ru.kn_n.testappforeffectivemobile

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import ru.kn_n.testappforeffectivemobile.di.component.DaggerAppComponent
import javax.inject.Inject

class Application: DaggerApplication() {
    private val applicationInjector = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}