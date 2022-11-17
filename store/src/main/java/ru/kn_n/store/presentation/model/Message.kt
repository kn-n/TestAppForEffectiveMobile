package ru.kn_n.store.presentation.model

import ru.kn_n.core.utils.Status

data class Message(
    val status: Status,
    val message: String
) : DisplayableItem