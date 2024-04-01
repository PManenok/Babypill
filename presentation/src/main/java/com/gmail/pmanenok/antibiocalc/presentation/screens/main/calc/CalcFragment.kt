package com.gmail.pmanenok.antibiocalc.presentation.screens.main.calc

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.databinding.FragmentCalcBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.domain.entity.types.MenuType

class CalcFragment : BaseMvvmFragment<CalcViewModel, MainRouter, FragmentCalcBinding>() {
    companion object {
        private const val DRUG_TYPE_EXTRA = "DRUG_TYPE_EXTRA"
        fun getInstance(drug: MenuType): CalcFragment {
            val fragment = CalcFragment()
            val bundle = Bundle()
            bundle.putSerializable(DRUG_TYPE_EXTRA, drug)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): CalcViewModel {
        return ViewModelProvider(this).get(CalcViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_calc

    override fun onResume() {
        super.onResume()
        getValues(arguments)

        viewModel.initiateValues()

        binding.calcChooseAntibioticRv.adapter = viewModel.adapter
        binding.calcChooseAntibioticRv.layoutManager = LinearLayoutManager(context)
        binding.calcChooseAntibioticRv.setHasFixedSize(true)
    }

    private fun getValues(arguments: Bundle?) {
        if (arguments != null) {
            viewModel.drugType = arguments.getSerializable(DRUG_TYPE_EXTRA) as MenuType
        }
    }
}
