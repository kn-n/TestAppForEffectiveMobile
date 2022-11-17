package ru.kn_n.product.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import ru.kn_n.core.presentation.BaseFragment
import ru.kn_n.core.utils.ArgumentsHolder
import ru.kn_n.product.databinding.ItemShopPagerBinding
import ru.kn_n.product.domain.model.ShopEntity
import ru.kn_n.product.presentation.adapter.CapacitiesAdapter
import ru.kn_n.product.presentation.adapter.ColorsAdapter
import ru.kn_n.product.presentation.adapter.ProductImagesAdapter

class ShopPageFragment: BaseFragment<ItemShopPagerBinding>(ItemShopPagerBinding::inflate), ArgumentsHolder<ShopEntity> {

    private val colorsAdapter by lazy { ColorsAdapter() }
    private val capacitiesAdapter by lazy { CapacitiesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapters()
        showInfo()
    }

    private fun setUpAdapters(){
        binding.colorsRecyclerView.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.memoryRecyclerView.apply {
            adapter = capacitiesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showInfo(){
        binding.camera.text = args.camera
        binding.cpu.text = args.cpu
        binding.sd.text = args.sd
        binding.ssd.text = args.ssd

        colorsAdapter.setItems(args.color)
        capacitiesAdapter.setItems(args.capacity)
    }

    companion object {
        fun newInstance(args: ShopEntity) = ShopPageFragment().apply {
            this.args = args
        }
    }
}