package ru.kn_n.store.presentation.model

import java.io.Serializable

data class FilterDialogScreenArgs(
    @Transient
    val onDoneClick: (String, String) -> Unit = { _: String, _: String -> }
) : Serializable