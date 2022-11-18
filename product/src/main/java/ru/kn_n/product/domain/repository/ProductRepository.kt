package ru.kn_n.product.domain.repository

import ru.kn_n.product.domain.model.ProductEntity

interface ProductRepository {
    suspend fun getProduct(): ProductEntity
}