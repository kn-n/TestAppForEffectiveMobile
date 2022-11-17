package ru.kn_n.store.presentation.model

import ru.kn_n.core.utils.ZERO
import javax.inject.Inject

class FilterCache @Inject constructor(){
    var brandFilterPosition: Int = Int.ZERO
    var priceFilterPosition: Int = Int.ZERO
    var sizeFilterPosition: Int = Int.ZERO
}
