package com.weightwatchers.ww_exercise_01.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.loadingview.LoadingDialog

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    private lateinit var loadingDialog: LoadingDialog
    lateinit var binding: T
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, getLayoutId(), container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            loadingDialog = LoadingDialog.get(it)
        }

        setup()
        viewModel.state.observe(viewLifecycleOwner, Observer { baseRender(it) })
    }

    abstract fun setup()

    private fun baseRender(state: ViewState) {
        when (state) {
            is ViewState.Loading -> showLoading()
            is ViewState.Error -> showError(state.error, state.errorResource)
            else -> {
                hideLoading()
                render(state)
            }
        }
    }

    abstract fun render(state: ViewState)

    // not private for the sake of overriding in case of custom implementation for specific screens
    open fun showLoading() {
        loadingDialog.show()
    }

    // not private for the sake of overriding in case of custom implementation for specific screens
    open fun hideLoading() {
        loadingDialog.hide()
    }

    open fun showError(errorMessage: String?, errorMessageRes:Int? = null) {
        hideLoading()
        val msg = if (errorMessageRes != null) context?.getString(errorMessageRes) else errorMessage
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}