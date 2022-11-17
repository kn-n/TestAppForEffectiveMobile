package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Binds
import dagger.Module
import ru.kn_n.cart.data.repository.CartRepository
import ru.kn_n.store.data.repository.PhonesProductsRepository
import ru.kn_n.core.data.Repository
import ru.kn_n.product.data.repository.ProductRepository
import javax.inject.Singleton

@Module(
    includes = [RetrofitClientModule::class]
)
abstract class RepositoriesModule {
    @Singleton
    @Binds
    abstract fun providePhonesProductsRepository(phonesProductsRepository: PhonesProductsRepository): Repository

    @Singleton
    @Binds
    abstract fun provideProductRepository(productRepository: ProductRepository): Repository

    @Singleton
    @Binds
    abstract fun provideCartRepository(cartRepository: CartRepository): Repository
}