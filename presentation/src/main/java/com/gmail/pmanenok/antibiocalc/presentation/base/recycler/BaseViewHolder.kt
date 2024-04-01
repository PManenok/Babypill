package com.gmail.pmanenok.antibiocalc.presentation.base.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pmanenok.antibiocalc.BR


abstract class BaseViewHolder<Entity, VM : BaseItemViewModel<Entity>, Binding : ViewDataBinding>
    (val binding: Binding, val viewModel: VM) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.setVariable(BR.item, viewModel)
    }

    fun bind(item: Entity, position: Int) {
        viewModel.bindItem(item, position)
        binding.executePendingBindings()
    }
}