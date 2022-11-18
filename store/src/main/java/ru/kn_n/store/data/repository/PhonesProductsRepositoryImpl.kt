package ru.kn_n.store.data.repository

import ru.kn_n.store.data.api.StoreAPI
import ru.kn_n.store.data.mapper.FilterMapper
import ru.kn_n.store.data.mapper.PhonesProductsResponseMapper
import ru.kn_n.store.domain.repository.PhonesProductsRepository
import javax.inject.Inject

class PhonesProductsRepositoryImpl @Inject constructor(
    private val api: StoreAPI,
    private val mapperProducts: PhonesProductsResponseMapper,
    private val mapperFilterProducts: FilterMapper
) : PhonesProductsRepository {
    override suspend fun getPhonesProducts() = mapperProducts.mapPhonesProductResponse(api.getPhonesProducts())
    override suspend fun getFilterProducts(brand: String, price: String) = mapperFilterProducts.mapListBestSellerResponse(
        response = api.getPhonesProducts(),
        brand = brand,
        price = price
    )
}