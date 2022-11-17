package ru.kn_n.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import ru.kn_n.core.utils.Resource
import ru.kn_n.core.utils.Status

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
): DaggerFragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> showRequestResult(
        resource: Resource<T>,
        doOnSuccess: () -> Unit,
        doOnError: () -> Unit,
        doOnLoading: () -> Unit,
        doOnEmpty: () -> Unit
    ){
        when(resource.status) {
            Status.LOADING -> doOnLoading()
            Status.SUCCESS -> doOnSuccess()
            Status.ERROR -> doOnError()
            Status.EMPTY -> doOnEmpty()
        }
    }
}