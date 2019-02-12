package com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.recycler

import android.view.ViewGroup
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseRecyclerAdapter
import com.gmail.pmanenok.antibiocalc.presentation.base.recycler.BaseViewHolder
import com.gmail.pmanenok.domain.entity.Result

class ResultItemAdapter(onItemClick: (Result) -> Unit = {}, onItemLongClick: (Result) -> Unit = {}) :
    BaseRecyclerAdapter<Result, ResultItemViewModel>(onItemClick = onItemClick, onItemLongClick = onItemLongClick) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ResultItemViewHolder {
        return ResultItemViewHolder.create(
            viewGroup,
            ResultItemViewModel()
        )
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].getViewType()
    }
}