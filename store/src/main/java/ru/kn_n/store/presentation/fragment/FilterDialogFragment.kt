package ru.kn_n.store.presentation.fragment

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import ru.kn_n.core.utils.ArgumentsHolder
import ru.kn_n.core.utils.EMPTY
import ru.kn_n.store.R
import ru.kn_n.store.databinding.FilterBottomSheetBinding
import ru.kn_n.store.presentation.adapter.FilterAdapter
import ru.kn_n.store.presentation.model.FilterCache
import ru.kn_n.store.presentation.model.FilterDialogScreenArgs
import javax.inject.Inject

class FilterDialogFragment : BottomSheetDialogFragment(), ArgumentsHolder<FilterDialogScreenArgs> {

    private var _binding: FilterBottomSheetBinding? = null
    private val binding get() = _binding!!

    var androidInjector: DispatchingAndroidInjector<Any>? = null
        @Inject set

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }

    @Inject
    lateinit var cache: FilterCache

    private val brandsAdapter by lazy {
        FilterAdapter(
            context = requireContext(),
            items = requireContext().resources.getStringArray(R.array.brands).toList()
        )
    }

    private val sizesAdapter by lazy {
        FilterAdapter(
            context = requireContext(),
            items = requireContext().resources.getStringArray(R.array.sizes).toList()
        )
    }

    private val pricesAdapter by lazy {
        FilterAdapter(
            context = requireContext(),
            items = requireContext().resources.getStringArray(R.array.prices).toList()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setDimAmount(0F)
        initViews()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissAllowingStateLoss()
    }

    private fun initViews() {
        setUpFilters()
        binding.addFilter.setOnClickListener {
            cache.brandFilterPosition = binding.brandSpinner.selectedItemPosition
            cache.priceFilterPosition = binding.priceSpinner.selectedItemPosition
            cache.sizeFilterPosition = binding.sizeSpinner.selectedItemPosition

            args.onDoneClick(
                binding.brandSpinner.selectedItem
                    .toString()
                    .replace(requireContext().getString(R.string.no_filter), String.EMPTY),
                binding.priceSpinner.selectedItem
                    .toString()
                    .replace(requireContext().getString(R.string.no_filter), String.EMPTY)
            )
            dismissAllowingStateLoss()
        }
        binding.closeFilter.setOnClickListener { dismissAllowingStateLoss() }
    }

    private fun setUpFilters() {
        binding.brandSpinner.apply {
            adapter = brandsAdapter
            setSelection(cache.brandFilterPosition)
        }

        binding.sizeSpinner.apply {
            adapter = sizesAdapter
            setSelection(cache.sizeFilterPosition)
        }

        binding.priceSpinner.apply {
            adapter = pricesAdapter
            setSelection(cache.priceFilterPosition)
        }
    }

    companion object {
        fun newInstance(args: FilterDialogScreenArgs) = FilterDialogFragment().apply {
            this.args = args
        }
    }
}