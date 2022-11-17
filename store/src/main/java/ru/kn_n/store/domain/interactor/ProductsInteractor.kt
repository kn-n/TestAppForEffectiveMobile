package ru.kn_n.store.domain.interactor

import ru.kn_n.store.data.repository.PhonesProductsRepository
import javax.inject.Inject

class ProductsInteractor @Inject constructor(
    private val phonesProductsRepository: PhonesProductsRepository
) {
    suspend fun getPhonesProducts() = phonesProductsRepository.getPhonesProducts()
    suspend fun getFilterProducts(brand: String, price: String) =
        phonesProductsRepository.getFilterProducts(brand, price)
}