package ru.kn_n.product.domain.interactor

import ru.kn_n.product.data.repository.ProductRepositoryImpl
import javax.inject.Inject

class ProductInteractor @Inject constructor(
    private val productRepository: ProductRepositoryImpl
) {
    suspend fun getProductInfo() = productRepository.getProduct()
}