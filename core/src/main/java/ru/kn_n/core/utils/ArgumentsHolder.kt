package ru.kn_n.core.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

interface ArgumentsHolder<A : Serializable> {

    fun getArgumentManager(): ArgumentManager = SerializableArgumentManager()

    var args: A
        get() = getArgumentManager().extractArgument(this as Fragment)
        set(args) {
            getArgumentManager().putArgument(this as Fragment, args)
        }
}


interface ArgumentManager {
    fun <A> extractArgument(fragment: Fragment): A
    fun <A> putArgument(fragment: Fragment, args: A)
}

class SerializableArgumentManager : ArgumentManager {

    @Suppress("UNCHECKED_CAST")
    override fun <A> extractArgument(fragment: Fragment): A {
        val argument: Serializable = fragment.requireArguments().getSerializable(KEY_ARGUMENTS)
            ?: error("Аргумент не был добавлен в fragment")
        return argument as A
    }

    override fun <A> putArgument(fragment: Fragment, args: A) {
        fragment.arguments = (fragment.arguments ?: Bundle()).apply {
            putSerializable(KEY_ARGUMENTS, args as Serializable)
        }
    }

    companion object {
        private const val KEY_ARGUMENTS = "KEY_ARGUMENTS"
    }
}
