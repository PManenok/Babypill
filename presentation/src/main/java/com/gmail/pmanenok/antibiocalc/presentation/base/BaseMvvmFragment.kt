package com.gmail.pmanenok.antibiocalc.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gmail.pmanenok.antibiocalc.BR

abstract class BaseMvvmFragment<VM : BaseViewModel<R>, R : BaseRouter<*>, B : ViewDataBinding>
    : BaseFragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B
    protected var router: R? = null

    abstract fun prodiveViewModel(): VM

    abstract fun provideLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false)
        viewModel = prodiveViewModel()
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is BaseMvvmActivity<*, *, *>) {
            router = (activity as BaseMvvmActivity<*, *, *>).router as R
        }
    }

    override fun onDetach() {
        super.onDetach()
        router = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.addRouter(router)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeRouter()
    }
}