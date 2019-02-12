package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.databinding.FragmentResultBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.MainRouter
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.TypedEnum

class ResultFragment : BaseMvvmFragment<ResultViewModel, MainRouter, FragmentResultBinding>() {
    companion object {
        private const val DRUG_TYPE_EXTRA = "DRUG_TYPE_EXTRA"
        private const val DRUG_SUBTYPE_EXTRA = "DRUG_SUBTYPE_EXTRA"
        private const val WEIGHT_EXTRA = "WEIGHT_EXTRA"
        private const val DOSAGE_INDEX_EXTRA = "DOSAGE_INDEX_EXTRA"
        fun getInstance(drugType: MenuType, drug: TypedEnum, weight: Int, dosageInd: Int): ResultFragment {
            val fragment = ResultFragment()
            val bundle = Bundle()
            bundle.putSerializable(DRUG_TYPE_EXTRA, drugType)
            bundle.putSerializable(DRUG_SUBTYPE_EXTRA, drug)
            bundle.putInt(WEIGHT_EXTRA, weight)
            bundle.putInt(DOSAGE_INDEX_EXTRA, dosageInd)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): ResultViewModel {
        return ViewModelProviders.of(this).get(ResultViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_result

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getValues(arguments)

        binding.resultRv.adapter = viewModel.adapter
        binding.resultRv.layoutManager = LinearLayoutManager(context)
        binding.resultRv.setHasFixedSize(true)
    }

    private fun getValues(arguments: Bundle?) {
        if (arguments != null) {
            viewModel.drugType = arguments.getSerializable(DRUG_TYPE_EXTRA) as MenuType
            viewModel.drugSubtype = arguments.getSerializable(DRUG_SUBTYPE_EXTRA) as TypedEnum
            viewModel.weightValue = arguments.getInt(WEIGHT_EXTRA)
            viewModel.dosageIndex = arguments.getInt(DOSAGE_INDEX_EXTRA)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.initiateValues()
    }
}