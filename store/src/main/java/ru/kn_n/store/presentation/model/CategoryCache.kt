package ru.kn_n.store.presentation.model

import javax.inject.Inject

class CategoryCache @Inject constructor() {
    var lastCategory: String = "Phones"
}