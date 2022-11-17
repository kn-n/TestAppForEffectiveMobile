package ru.kn_n.testappforeffectivemobile.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.kn_n.cart.presentation.viewmodel.CartViewModel
import ru.kn_n.core.di.ViewModelFactory
import ru.kn_n.product.presentation.viewmodel.ProductViewModel
import ru.kn_n.testappforeffectivemobile.di.ViewModelKey
import ru.kn_n.testappforeffectivemobile.di.scopes.AppScoped
import ru.kn_n.store.presentation.viewmodel.StoreViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StoreViewModel::class)
    abstract fun bindStoreViewModel(viewModel: StoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViewModel(viewModel: ProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}