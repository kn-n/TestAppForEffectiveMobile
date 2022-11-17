package ru.kn_n.store.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.core.presentation.BaseFragment
import ru.kn_n.core.utils.*
import ru.kn_n.store.R
import ru.kn_n.store.databinding.FragmentStoreBinding
import ru.kn_n.store.presentation.adapter.CategoriesAdapter
import ru.kn_n.store.presentation.adapter.MainStoreAdapter
import ru.kn_n.store.presentation.model.DisplayableItem
import ru.kn_n.store.presentation.model.FilterDialogScreenArgs
import ru.kn_n.store.presentation.model.Message
import ru.kn_n.store.presentation.viewmodel.StoreViewModel
import javax.inject.Inject

class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: StoreViewModel by viewModels { viewModelFactory }

    private val mainAdapter by lazy {
        MainStoreAdapter(
            onProductClick = { onProductClick() },
            onRefreshClick = { onRefreshClick() }
        )
    }

    private val categoriesAdapter by lazy {
        CategoriesAdapter { category ->
            onCategoryClick(category)
        }
    }

    private var lastCategory = String.EMPTY

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapters()
        showCategories()
        showTapBar()

        lastCategory = requireContext().getString(R.string.phones)
        getProductsCategory(lastCategory)
        getCartItemCount()

        initFilter()
        onCartClick()
    }

    private fun showTapBar() {
        binding.tapBar.root.show()
    }

    private fun initFilter() {
        binding.filter.setOnClickListener {
            if (lastCategory == requireContext().getString(R.string.phones)) {
                FilterDialogFragment.newInstance(
                    FilterDialogScreenArgs(
                        onDoneClick = ::getFilterProducts
                    )
                ).show(childFragmentManager, TAG)
            }
        }
    }

    private fun setUpAdapters() {
        binding.categoryRecyclerView.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            itemAnimator = DefaultItemAnimator()
        }

        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun showCategories() {
        viewModel.getCategories()
        viewModel.categoriesList.observe(viewLifecycleOwner) {
            categoriesAdapter.setItems(it)
        }
    }

    private fun getProductsCategory(category: String) {
        viewModel.getProductsFromCategory(category)
        viewModel.result.observe(viewLifecycleOwner) {
            showResult(it)
        }
    }

    private fun getFilterProducts(brand: String, price: String) {
        viewModel.getProductsWithFilter(
            category = lastCategory,
            brand = brand,
            price = price
        )
        viewModel.result.observe(viewLifecycleOwner) {
            showResult(it)
        }
    }

    private fun showResult(resource: Resource<List<DisplayableItem>>) {
        resource.let {
            showRequestResult(
                resource = it,
                doOnSuccess = { it.data?.let { data -> showData(data = data) } },
                doOnError = { showError() },
                doOnLoading = { showLoading() },
                doOnEmpty = { showEmpty() })
        }
    }

    private fun getCartItemCount() {
        viewModel.getCartNumber()
        viewModel.cartResult.observe(viewLifecycleOwner) {
            it.let { resource ->
                showRequestResult(
                    resource = resource,
                    doOnSuccess = { resource.data?.let { data -> showCartItemsCount(data = data) } },
                    doOnError = { fun error() {} },
                    doOnLoading = { fun loading() {} },
                    doOnEmpty = { fun empty() {} }
                )
            }
        }
    }

    private fun showCartItemsCount(data: CartEntity) {
        if (data.basket.isNotEmpty()) {
            binding.tapBar.badge.text = data.basket.size.toString()
            binding.tapBar.badge.show()
        } else {
            binding.tapBar.badge.gone()
        }
    }

    private fun onCartClick() {
        binding.tapBar.cart.setOnClickListener {
            binding.tapBar.root.gone()
            viewModel.toCartFragment()
        }
    }

    private fun onCategoryClick(category: String) {
        getProductsCategory(category)
        lastCategory = category
    }

    private fun onProductClick() {
        viewModel.toProductFragment()
    }

    private fun onRefreshClick() {
        getProductsCategory(lastCategory)
    }

    private fun showLoading() {
        mainAdapter.apply {
            items = listOf(
                Message(
                    status = Status.LOADING,
                    message = String.EMPTY
                )
            )
            notifyDataSetChanged()
        }
    }


    private fun showData(data: List<DisplayableItem>) {
        mainAdapter.apply {
            items = data
            notifyDataSetChanged()
        }
    }

    private fun showEmpty() {
        mainAdapter.apply {
            items = listOf(
                Message(
                    status = Status.EMPTY,
                    message = requireContext().getString(ru.kn_n.core.R.string.empty_message)
                )
            )
            notifyDataSetChanged()
        }
    }

    private fun showError() {
        mainAdapter.apply {
            items = listOf(
                Message(
                    status = Status.ERROR,
                    message = requireContext().getString(ru.kn_n.core.R.string.error_message)
                )
            )
            notifyDataSetChanged()
        }
    }

    companion object{
        private const val TAG = "filterDialogFragment"
    }
}