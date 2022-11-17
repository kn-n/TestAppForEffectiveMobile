package ru.kn_n.product.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import ru.kn_n.cart.domain.interactor.CartInteractor
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.core.presentation.BaseViewModel
import ru.kn_n.core.utils.Resource
import ru.kn_n.product.domain.interactor.ProductInteractor
import ru.kn_n.product.domain.model.ProductEntity
import ru.kn_n.product.presentation.navigation.Screens
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val productInteractor: ProductInteractor,
    private val cartInteractor: CartInteractor,
    private val router: Router
) : BaseViewModel() {

    private val _result = MutableLiveData<Resource<ProductEntity>>()
    val result: LiveData<Resource<ProductEntity>> = _result

    fun getProductInfo() {
        requestWithLiveData(_result) { productInteractor.getProductInfo() }
    }

    private val _cartResult = MutableLiveData<Resource<CartEntity>>()
    val cartResult: LiveData<Resource<CartEntity>> = _cartResult

    fun getCartNumber() {
        requestWithLiveData(_cartResult) {cartInteractor.getCartInfo()}
    }

    fun backToStoreFragment() {
        router.exit()
    }

    fun toCartFragment() {
        router.navigateTo(Screens.cart())
    }
}