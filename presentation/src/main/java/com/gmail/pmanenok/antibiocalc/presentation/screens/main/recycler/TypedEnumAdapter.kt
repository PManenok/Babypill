package com.gmail.pmanenok.antibiocalc.presentation.screens.main.recycler

import android.view.ViewGroup
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseRecyclerAdapter
import com.gmail.pmanenok.domain.entity.types.TypedEnum
import io.reactivex.processors.BehaviorProcessor

class TypedEnumAdapter(
    val taped: BehaviorProcessor<Int>? = null,
    onItemClick: (TypedEnum) -> Unit = {},
    onItemLongClick: (TypedEnum) -> Unit = {}
) :
    BaseRecyclerAdapter<TypedEnum, TypedEnumViewModel>(onItemClick = onItemClick, onItemLongClick = onItemLongClick) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TypedEnumViewHolder {
        return TypedEnumViewHolder.create(
            viewGroup,
            TypedEnumViewModel(taped)
        )
    }
}