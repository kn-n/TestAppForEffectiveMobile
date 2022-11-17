package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kn_n.cart.presentation.fragment.CartFragment
import ru.kn_n.product.presentation.fragment.EmptyPageFragment
import ru.kn_n.product.presentation.fragment.ProductFragment
import ru.kn_n.product.presentation.fragment.ShopPageFragment
import ru.kn_n.store.presentation.fragment.FilterDialogFragment
import ru.kn_n.testappforeffectivemobile.di.scopes.FragmentScoped
import ru.kn_n.store.presentation.fragment.StoreFragment

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun storeFragment(): StoreFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun productFragment(): ProductFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun shopPageFragment(): ShopPageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun emptyPageFragment(): EmptyPageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun filterDialogFragment(): FilterDialogFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun cartFragment(): CartFragment
}