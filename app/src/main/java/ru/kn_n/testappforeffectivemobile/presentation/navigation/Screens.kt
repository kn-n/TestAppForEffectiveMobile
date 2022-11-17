package ru.kn_n.testappforeffectivemobile.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kn_n.cart.presentation.fragment.CartFragment
import ru.kn_n.product.presentation.fragment.ProductFragment
import ru.kn_n.store.presentation.fragment.StoreFragment

object Screens {
    fun store() = FragmentScreen { StoreFragment() }
}