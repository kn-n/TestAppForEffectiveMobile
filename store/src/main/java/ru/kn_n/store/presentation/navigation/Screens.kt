package ru.kn_n.store.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kn_n.cart.presentation.fragment.CartFragment
import ru.kn_n.product.presentation.fragment.ProductFragment

object Screens {
    fun product() = FragmentScreen{ ProductFragment() }
    fun cart() = FragmentScreen{ CartFragment() }
}