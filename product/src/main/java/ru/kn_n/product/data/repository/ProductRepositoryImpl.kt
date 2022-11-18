package ru.kn_n.product.data.repository

import ru.kn_n.product.data.api.ProductAPI
import ru.kn_n.product.data.mapper.ProductResponseMapper
import ru.kn_n.product.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductAPI,
    private val mapper: ProductResponseMapper
) : ProductRepository {
    override suspend fun getProduct() = mapper.mapProductResponse(api.getProductInfo())
}