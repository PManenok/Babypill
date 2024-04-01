package com.gmail.pmanenok.antibiocalc.presentation.base.recycler

import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerAdapter<Entity, VM : BaseItemViewModel<Entity>>
    (
    val itemList: MutableList<Entity> = mutableListOf(),
    val onItemClick: (Entity) -> Unit = {},
    val onItemLongClick: (Entity) -> Unit = {}
) : RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>() {
    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    val clickItemSubject = PublishSubject.create<ItemClick<Entity>>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM, *>, position: Int) {
        holder.bind(itemList[position], position)
        addToDisposable(holder.viewModel.disposable)
    }

    fun addItems(items: List<Entity>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    protected fun addToDisposable(disposable: Disposable?) {
        if (disposable != null)
            compositeDisposable.add(disposable)
    }

    fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val pos = holder.adapterPosition
            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
            holder.viewModel.onItemClick()
            onItemClick(itemList[pos])
        }
        holder.itemView.setOnLongClickListener {
            val pos = holder.adapterPosition
            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
            onItemLongClick(itemList[pos])
            return@setOnLongClickListener true
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
        holder.itemView.setOnLongClickListener(null)
    }
}