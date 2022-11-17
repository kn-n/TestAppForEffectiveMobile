package ru.kn_n.product.domain.interactor

import ru.kn_n.product.data.repository.ProductRepository
import javax.inject.Inject

class ProductInteractor @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun getProductInfo() = productRepository.getProduct()
}