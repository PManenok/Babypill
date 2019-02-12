package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.pmanenok.antibiocalc.databinding.ItemDrugResultBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseViewHolder
import com.gmail.pmanenok.domain.entity.Result

class ResultItemViewHolder(binding: ItemDrugResultBinding, viewModel: ResultItemViewModel) :
    BaseViewHolder<Result, ResultItemViewModel, ItemDrugResultBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: ResultItemViewModel): ResultItemViewHolder {
            val binding = ItemDrugResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ResultItemViewHolder(
                binding,
                viewModel
            )
        }
    }
}