package ru.kn_n.cart.domain.interactor

import ru.kn_n.cart.data.repository.CartRepository
import javax.inject.Inject

class CartInteractor @Inject constructor(
    private val cartRepository: CartRepository
){
    suspend fun getCartInfo() = cartRepository.getCart()
}