package ru.kn_n.cart.domain.interactor

import ru.kn_n.cart.data.repository.CartRepositoryImpl
import javax.inject.Inject

class CartInteractor @Inject constructor(
    private val cartRepository: CartRepositoryImpl
){
    suspend fun getCartInfo() = cartRepository.getCart()
}