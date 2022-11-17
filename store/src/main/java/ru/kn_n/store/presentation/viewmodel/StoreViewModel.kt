package ru.kn_n.store.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import ru.kn_n.cart.domain.interactor.CartInteractor
import ru.kn_n.cart.domain.model.CartEntity
import ru.kn_n.core.presentation.BaseViewModel
import ru.kn_n.core.utils.Resource
import ru.kn_n.core.utils.ResourceProvider
import ru.kn_n.store.R
import ru.kn_n.store.domain.interactor.ProductsInteractor
import ru.kn_n.store.presentation.mapper.EntityToListDisplayableItemsMapper
import ru.kn_n.store.presentation.model.Category
import ru.kn_n.store.presentation.model.DisplayableItem
import ru.kn_n.store.presentation.navigation.Screens
import javax.inject.Inject

class StoreViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor,
    private val mapper: EntityToListDisplayableItemsMapper,
    private val resourceProvider: ResourceProvider,
    private val cartInteractor: CartInteractor,
    private val router: Router
) : BaseViewModel() {

    private val _categoriesList = MutableLiveData<List<Category>>()
    val categoriesList: LiveData<List<Category>> = _categoriesList

    private val _result = MutableLiveData<Resource<List<DisplayableItem>>>()
    val result: LiveData<Resource<List<DisplayableItem>>> = _result

    fun getProductsFromCategory(category: String) {
        when (category) {
            resourceProvider.getString(R.string.phones) -> {
                requestWithLiveData(_result) { mapper.mapPhonesProductsEntity(productsInteractor.getPhonesProducts()) }
            }
            else -> _result.postValue(Resource.empty(data = null))
        }
    }

    private fun getProductsWithFilters(category: String, brand: String, price: String) {
        when (category) {
            resourceProvider.getString(R.string.phones) -> {
                requestWithLiveData(
                    _result
                ) { mapper.mapPhonesProductsEntity(productsInteractor.getFilterProducts(brand, price)) }
            }
            else -> _result.postValue(Resource.empty(data = null))
        }
    }

    fun getProductsWithFilter(category: String, brand: String, price: String) {
        if (brand.isEmpty() && price.isEmpty()) {
            getProductsFromCategory(category)
        } else {
            getProductsWithFilters(
                category = category,
                brand = brand,
                price = price
            )
        }
    }

    fun getCategories() {
        val categoriesList = ArrayList<Category>()
        categoriesList.addAll(
            listOf(
                Category(
                    categoryName = resourceProvider.getString(R.string.phones),
                    categoryImage = R.drawable.ic_phone_24dp
                ),
                Category(
                    categoryName = resourceProvider.getString(R.string.computer),
                    categoryImage = R.drawable.ic_computer_24dp
                ),
                Category(
                    categoryName = resourceProvider.getString(R.string.health),
                    categoryImage = R.drawable.ic_health_24dp
                ),
                Category(
                    categoryName = resourceProvider.getString(R.string.books),
                    categoryImage = R.drawable.ic_books_24dp
                )
            )
        )
        _categoriesList.postValue(categoriesList)
    }

    private val _cartResult = MutableLiveData<Resource<CartEntity>>()
    val cartResult: LiveData<Resource<CartEntity>> = _cartResult

    fun getCartNumber() {
        requestWithLiveData(_cartResult) {cartInteractor.getCartInfo()}
    }

    fun toProductFragment() {
        router.navigateTo(Screens.product())
    }

    fun toCartFragment(){
        router.navigateTo(Screens.cart())
    }
}