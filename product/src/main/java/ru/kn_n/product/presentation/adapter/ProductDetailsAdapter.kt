package ru.kn_n.product.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kn_n.product.domain.model.ShopEntity
import ru.kn_n.product.presentation.fragment.EmptyPageFragment
import ru.kn_n.product.presentation.fragment.ShopPageFragment

class ProductDetailsAdapter(
    fa: FragmentActivity,
    private val listOfTitle: List<String>,
    private val shopItem: ShopEntity
) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int = listOfTitle.size

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ShopPageFragment.newInstance(shopItem)
            1 -> return EmptyPageFragment()
            2 -> return EmptyPageFragment()
        }
        return ShopPageFragment()
    }

}