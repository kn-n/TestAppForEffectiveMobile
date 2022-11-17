package ru.kn_n.store.data.repository

import ru.kn_n.core.data.Repository
import ru.kn_n.store.data.api.StoreAPI
import ru.kn_n.store.data.mapper.FilterMapper
import ru.kn_n.store.data.mapper.PhonesProductsResponseMapper
import javax.inject.Inject

class PhonesProductsRepository @Inject constructor(
    private val api: StoreAPI,
    private val mapperProducts: PhonesProductsResponseMapper,
    private val mapperFilterProducts: FilterMapper
) : Repository {
    suspend fun getPhonesProducts() = mapperProducts.mapPhonesProductResponse(api.getPhonesProducts())
    suspend fun getFilterProducts(brand: String, price: String) = mapperFilterProducts.mapListBestSellerResponse(
        response = api.getPhonesProducts(),
        brand = brand,
        price = price
    )
}