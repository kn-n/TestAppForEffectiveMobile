package ru.kn_n.product.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.core.presentation.BaseFragment
import ru.kn_n.core.utils.gone
import ru.kn_n.core.utils.show
import ru.kn_n.product.R
import ru.kn_n.product.databinding.FragmentProductBinding
import ru.kn_n.product.domain.model.ProductEntity
import ru.kn_n.product.domain.model.ShopEntity
import ru.kn_n.product.presentation.adapter.ProductDetailsAdapter
import ru.kn_n.product.presentation.adapter.ProductImagesAdapter
import ru.kn_n.product.presentation.adapter.ProductImagesViewPagerTransformer
import ru.kn_n.product.presentation.viewmodel.ProductViewModel
import javax.inject.Inject

class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProductViewModel by viewModels { viewModelFactory }

    private val productImagesAdapter by lazy { ProductImagesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        getProductInfo()
        getCartItemCount()

        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.backToStoreFragment()
        }

        binding.cart.setOnClickListener {
            viewModel.toCartFragment()
        }

        binding.statusLayout.refresh.setOnClickListener {
            getProductInfo()
        }
    }

    private fun setUpAdapter() {
        binding.productViewPager.apply {
            adapter = productImagesAdapter
            setPageTransformer(ProductImagesViewPagerTransformer())
            offscreenPageLimit = 3
        }
    }

    private fun getProductInfo() {
        viewModel.getProductInfo()

        viewModel.result.observe(viewLifecycleOwner) {
            it.let { resource ->
                showRequestResult(
                    resource = resource,
                    doOnSuccess = { resource.data?.let { data -> showData(data) } },
                    doOnError = { showError(resource.message.toString()) },
                    doOnLoading = { showLoading() },
                    doOnEmpty = { showEmpty() }
                )
            }
        }
    }

    private fun getCartItemCount(){
        viewModel.getCartNumber()
        viewModel.cartResult.observe(viewLifecycleOwner) {
            it.let { resource ->
                showRequestResult(
                    resource = resource,
                    doOnSuccess = { resource.data?.let { data -> showCartItemsCount(data = data) } },
                    doOnError = {fun error(){} },
                    doOnLoading = {fun loading(){} },
                    doOnEmpty = {fun empty(){} }
                )
            }
        }
    }

    private fun showCartItemsCount(data: CartEntity){
        if (data.basket.isNotEmpty()){
            binding.badge.text = data.basket.size.toString()
            binding.badge.show()
        } else {
            binding.badge.gone()
        }
    }

    private fun showData(data: ProductEntity) {
        binding.statusLayout.root.gone()
        binding.productName.text = data.productName
        binding.btnFavorite.isChecked = data.isFavorite
        binding.ratingBar.rating = data.rating
        binding.price.text = data.price

        productImagesAdapter.setItems(data.images)
        setUpProductDetailsVewPager(data.shopInfo)
    }

    private fun showError(error: String) {
        binding.statusLayout.apply {
            root.show()
            message.show()
            refresh.show()
            loading.gone()
            message.text = requireContext().getText(ru.kn_n.core.R.string.error_message)
        }
    }

    private fun showLoading() {
        binding.statusLayout.apply {
            root.show()
            root.setBackgroundColor(requireContext().getColor(ru.kn_n.core.R.color.gray_1))
            message.setTextColor(requireContext().getColor(ru.kn_n.core.R.color.dark_blue))
            message.gone()
            refresh.gone()
            loading.show()
        }
    }

    private fun showEmpty() {
        binding.statusLayout.apply {
            root.show()
            message.show()
            refresh.gone()
            loading.gone()
            message.text = requireContext().getText(ru.kn_n.core.R.string.empty_message)
        }
    }

    private fun setUpProductDetailsVewPager(shop: ShopEntity) {
        val pageAdapter = ProductDetailsAdapter(requireActivity(), listOfTitles, shop)
        binding.productDetailsViewPager.adapter = pageAdapter

        TabLayoutMediator(
            binding.tabLayout,
            binding.productDetailsViewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitles[position]
        }.attach()
    }

    companion object {
        private val listOfTitles = listOf("Shop", "Details", "Features")
    }
}