package ru.kn_n.cart.data.mapper

import ru.kn_n.cart.data.model.BasketResponse
import ru.kn_n.cart.data.model.CartResponse
import ru.kn_n.cart.domain.model.BasketEntity
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.core.utils.EMPTY
import ru.kn_n.core.utils.ZERO
import ru.kn_n.core.utils.toStringInMoneyFormat
import javax.inject.Inject

class CartResponseMapper @Inject constructor() {
    fun mapCartResponse(response: CartResponse): CartEntity {
        return CartEntity(
            basket =
            if (!response.basket.isNullOrEmpty())
                response.basket.map { mapBasketResponse(it) }
            else
                emptyList(),
            delivery = response.delivery ?: String.EMPTY,
            id = response.id ?: String.EMPTY,
            totalPrice = if (response.total != null) response.total.toStringInMoneyFormat else String.EMPTY
        )
    }

    private fun mapBasketResponse(response: BasketResponse): BasketEntity {
        return BasketEntity(
            id = response.id ?: Int.ZERO,
            productImage = response.images ?: String.EMPTY,
            price = if (response.price != null) response.price.toStringInMoneyFormat else String.EMPTY,
            productName = response.title ?: String.EMPTY
        )
    }
}