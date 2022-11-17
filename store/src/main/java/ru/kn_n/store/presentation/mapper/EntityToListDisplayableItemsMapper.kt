package ru.kn_n.store.presentation.mapper

import ru.kn_n.core.utils.ResourceProvider
import ru.kn_n.core.utils.toStringInMoneyFormat
import ru.kn_n.store.R
import ru.kn_n.store.domain.model.BestSellerProductEntity
import ru.kn_n.store.domain.model.HotSaleProductEntity
import ru.kn_n.store.domain.model.PhonesProductsEntity
import ru.kn_n.store.presentation.model.*
import javax.inject.Inject

class EntityToListDisplayableItemsMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {
    fun mapPhonesProductsEntity(data: PhonesProductsEntity): List<DisplayableItem> {
        val displayableItems = ArrayList<DisplayableItem>()
        if (data.hotSales.isNotEmpty()) {
            displayableItems.add(SectionTitle(resourceProvider.getString(R.string.title_hot_sales)))
            displayableItems.add(SliderBanner(mapListHotSalesProductEntity(data.hotSales)))
        }
        if (data.bestSeller.isNotEmpty()) {
            displayableItems.add(SectionTitle(resourceProvider.getString(R.string.title_best_seller)))
            displayableItems.add(GoodsGrid(mapListBestSellerProductEntity(data.bestSeller)))
        }
        return displayableItems.toList()
    }

    private fun mapListHotSalesProductEntity(data: List<HotSaleProductEntity>): List<SliderBannerPage> {
        return data.map { mapHotSalesProductEntity(it) }
    }

    private fun mapHotSalesProductEntity(data: HotSaleProductEntity): SliderBannerPage {
        return SliderBannerPage(
            productName = data.productName,
            productDescription = data.productDescription,
            imageUrl = data.imageUrl,
            isNew = data.isNew,
            isBuy = data.isBuy
        )
    }

    private fun mapListBestSellerProductEntity(data: List<BestSellerProductEntity>): List<Goods> {
        return data.map { mapBestSellerProductEntity(it) }
    }

    private fun mapBestSellerProductEntity(data: BestSellerProductEntity): Goods {
        return Goods(
            isFavorite = data.isFavorite,
            imageUrl = data.imageUrl,
            price = data.price.toStringInMoneyFormat,
            discountPrice = data.discountPrice.toStringInMoneyFormat,
            productName = data.productName
        )
    }
}