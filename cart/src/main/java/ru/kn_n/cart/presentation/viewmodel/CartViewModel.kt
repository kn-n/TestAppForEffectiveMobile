package ru.kn_n.cart.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import ru.kn_n.cart.domain.interactor.CartInteractor
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.core.presentation.BaseViewModel
import ru.kn_n.core.utils.Resource
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartInteractor: CartInteractor,
    private val router: Router
) : BaseViewModel() {

    private val _result = MutableLiveData<Resource<CartEntity>>()
    val result: LiveData<Resource<CartEntity>> = _result

    fun getCartInfo() {
        requestWithLiveData(_result) { cartInteractor.getCartInfo() }
    }

    fun back() {
        router.exit()
    }
}