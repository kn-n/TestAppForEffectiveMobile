package ru.kn_n.product.data.repository

import ru.kn_n.core.data.Repository
import ru.kn_n.product.data.api.ProductAPI
import ru.kn_n.product.data.mapper.ProductResponseMapper
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductAPI,
    private val mapper: ProductResponseMapper
) : Repository {
    suspend fun getProduct() = mapper.mapProductResponse(api.getProductInfo())
}