package ru.kn_n.cart.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kn_n.cart.R
import ru.kn_n.cart.databinding.FragmentCartBinding
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.cart.presentation.adapter.CartItemsAdapter
import ru.kn_n.cart.presentation.viewmodel.CartViewModel
import ru.kn_n.core.presentation.BaseFragment
import ru.kn_n.core.utils.gone
import ru.kn_n.core.utils.show
import javax.inject.Inject

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CartViewModel by viewModels { viewModelFactory }

    private val cartItemsAdapter by lazy { CartItemsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        getCartInfo()

        onBackPressed()
    }

    private fun onBackPressed(){
        binding.btnBack.setOnClickListener {
            viewModel.back()
        }
    }

    private fun setUpAdapter(){
        binding.cartRecyclerView.apply {
            adapter = cartItemsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getCartInfo(){
        viewModel.getCartInfo()
        viewModel.result.observe(viewLifecycleOwner) {
            it.let { resource ->
                showRequestResult(
                    resource = resource,
                    doOnSuccess = { resource.data?.let { data -> showCartInfo(data) } },
                    doOnError = { showError(resource.message.toString()) },
                    doOnLoading = { showLoading() },
                    doOnEmpty = { showEmpty() }
                )
            }
        }
    }

    private fun showCartInfo(data: CartEntity){
        binding.statusLayout.root.gone()
        binding.totalAmount.text = data.totalPrice
        binding.deliveryCost.text = data.delivery

        cartItemsAdapter.setItems(data.basket)
    }

    private fun showLoading(){
        binding.statusLayout.apply {
            root.show()
            root.setBackgroundResource(ru.kn_n.core.R.drawable.bg_blue_radius_30dp)
            message.setTextColor(requireContext().getColor(ru.kn_n.core.R.color.white))
            message.gone()
            refresh.gone()
            loading.show()
        }
    }

    private fun showError(error: String){
        binding.statusLayout.apply {
            root.show()
            message.show()
            refresh.show()
            loading.gone()
            message.text = requireContext().getText(ru.kn_n.core.R.string.error_message)
        }
    }

    private fun showEmpty(){
        binding.statusLayout.apply {
            root.show()
            message.show()
            refresh.gone()
            loading.gone()
            message.text = requireContext().getText(ru.kn_n.core.R.string.empty_message)
        }
    }
}