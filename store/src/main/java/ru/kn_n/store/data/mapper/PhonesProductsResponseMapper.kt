package ru.kn_n.store.data.mapper

import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO
import ru.kn_n.store.data.model.BestSellerResponse
import ru.kn_n.store.data.model.HomeStoreResponse
import ru.kn_n.store.data.model.PhonesProductsResponse
import ru.kn_n.store.domain.model.BestSellerProductEntity
import ru.kn_n.store.domain.model.HotSaleProductEntity
import ru.kn_n.store.domain.model.PhonesProductsEntity
import javax.inject.Inject

class PhonesProductsResponseMapper @Inject constructor() {
    fun mapPhonesProductResponse(response: PhonesProductsResponse): PhonesProductsEntity {
        return PhonesProductsEntity(
            hotSales =
            if (!response.home_store.isNullOrEmpty())
                mapListHomeStoreResponse(response.home_store)
            else emptyList(),
            bestSeller =
            if (!response.best_seller.isNullOrEmpty())
                mapListBestSellerResponse(response.best_seller)
            else emptyList()
        )
    }

    private fun mapListHomeStoreResponse(response: List<HomeStoreResponse>): List<HotSaleProductEntity> {
        return response.map { mapHomeStoreResponse(it) }
    }

    private fun mapHomeStoreResponse(response: HomeStoreResponse): HotSaleProductEntity {
        return HotSaleProductEntity(
            id = response.id ?: Int.ZERO,
            isNew = response.is_new ?: false,
            productName = response.title ?: String.EMPTY,
            productDescription = response.subtitle ?: String.EMPTY,
            imageUrl = response.picture ?: String.EMPTY,
            isBuy = response.is_buy ?: false
        )
    }

    private fun mapListBestSellerResponse(response: List<BestSellerResponse>): List<BestSellerProductEntity> {
        return response.map { mapBestSellerResponse(it) }
    }

    private fun mapBestSellerResponse(response: BestSellerResponse): BestSellerProductEntity {
        return BestSellerProductEntity(
            id = response.id ?: Int.ZERO,
            isFavorite = response.is_favorites ?: false,
            productName = response.title ?: String.EMPTY,
            price = response.discount_price ?: Int.ZERO,
            discountPrice = response.price_without_discount ?: Int.ZERO,
            imageUrl = response.picture ?: String.EMPTY
        )
    }
}