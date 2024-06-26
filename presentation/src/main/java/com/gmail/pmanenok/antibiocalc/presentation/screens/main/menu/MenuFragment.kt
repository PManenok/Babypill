package com.gmail.pmanenok.antibiocalc.presentation.screens.main.menu

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.databinding.FragmentMenuBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter

class MenuFragment : BaseMvvmFragment<MenuViewModel, MainRouter, FragmentMenuBinding>() {
    companion object {
        fun getInstance(): MenuFragment {
            val fragment = MenuFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): MenuViewModel {
        return ViewModelProvider(this).get(MenuViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_menu

    override fun onResume() {
        super.onResume()

        binding.menuRv.adapter = viewModel.adapter
        binding.menuRv.layoutManager = LinearLayoutManager(context)
        binding.menuRv.setHasFixedSize(true)
    }
}
