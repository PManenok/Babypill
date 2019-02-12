package com.gmail.pmanenok.antibiocalc.presentation.screens.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.pmanenok.antibiocalc.databinding.ItemTypedEnumBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseViewHolder
import com.gmail.pmanenok.domain.entity.types.TypedEnum

class TypedEnumViewHolder(binding: ItemTypedEnumBinding, viewModel: TypedEnumViewModel) :
    BaseViewHolder<TypedEnum, TypedEnumViewModel, ItemTypedEnumBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: TypedEnumViewModel): TypedEnumViewHolder {
            val binding = ItemTypedEnumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TypedEnumViewHolder(
                binding,
                viewModel
            )
        }
    }
}