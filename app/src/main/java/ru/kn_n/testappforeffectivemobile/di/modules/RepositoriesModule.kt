package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Binds
import dagger.Module
import ru.kn_n.cart.data.repository.CartRepositoryImpl
import ru.kn_n.cart.domain.repository.CartRepository
import ru.kn_n.store.data.repository.PhonesProductsRepositoryImpl
import ru.kn_n.product.data.repository.ProductRepositoryImpl
import ru.kn_n.product.domain.repository.ProductRepository
import ru.kn_n.store.domain.repository.PhonesProductsRepository
import javax.inject.Singleton

@Module(
    includes = [RetrofitClientModule::class]
)
abstract class RepositoriesModule {
    @Singleton
    @Binds
    abstract fun providePhonesProductsRepository(
        phonesProductsRepository: PhonesProductsRepositoryImpl
    ): PhonesProductsRepository

    @Singleton
    @Binds
    abstract fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun provideCartRepository(cartRepository: CartRepositoryImpl): CartRepository
}