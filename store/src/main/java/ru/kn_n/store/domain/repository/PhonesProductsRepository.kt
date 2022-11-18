package ru.kn_n.store.domain.repository

import ru.kn_n.store.domain.model.PhonesProductsEntity

interface PhonesProductsRepository {
    suspend fun getPhonesProducts(): PhonesProductsEntity
    suspend fun getFilterProducts(brand: String, price: String): PhonesProductsEntity
}