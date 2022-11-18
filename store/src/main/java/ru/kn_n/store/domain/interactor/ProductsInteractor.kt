package ru.kn_n.store.domain.interactor

import ru.kn_n.store.data.repository.PhonesProductsRepositoryImpl
import javax.inject.Inject

class ProductsInteractor @Inject constructor(
    private val phonesProductsRepository: PhonesProductsRepositoryImpl
) {
    suspend fun getPhonesProducts() = phonesProductsRepository.getPhonesProducts()
    suspend fun getFilterProducts(brand: String, price: String) =
        phonesProductsRepository.getFilterProducts(brand, price)
}