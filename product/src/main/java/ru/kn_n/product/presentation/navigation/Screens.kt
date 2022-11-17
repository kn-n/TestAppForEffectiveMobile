package ru.kn_n.product.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kn_n.cart.presentation.fragment.CartFragment

object Screens {
    fun cart() = FragmentScreen{ CartFragment() }
}