package ru.kn_n.testappforeffectivemobile.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kn_n.cart.data.api.CartAPI
import ru.kn_n.core.utils.Constants.BASE_URL
import ru.kn_n.product.data.api.ProductAPI
import ru.kn_n.store.data.api.StoreAPI
import javax.inject.Singleton

@Module
class RetrofitClientModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideStoreAPI(retrofit: Retrofit): StoreAPI = retrofit.create(StoreAPI::class.java)

    @Singleton
    @Provides
    fun provideProductAPI(retrofit: Retrofit): ProductAPI = retrofit.create(ProductAPI::class.java)

    @Singleton
    @Provides
    fun provideCartAPI(retrofit: Retrofit): CartAPI = retrofit.create(CartAPI::class.java)
}