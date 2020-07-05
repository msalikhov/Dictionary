package com.msalikhov.dictionary.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object ListAdapterBuilder {

    @JvmSynthetic
    inline fun <T, VH> build(
        @LayoutRes layoutRes: Int,
        crossinline viewHolderCreator: (view: View) -> VH
    ) where VH : RecyclerView.ViewHolder, VH : (T) -> Unit, T : Diffable<T> =
        build { inflater, parent -> viewHolderCreator(inflater.inflate(layoutRes, parent, false)) }

    inline fun <T, VH> build(crossinline viewHolderCreator: (LayoutInflater, ViewGroup) -> VH): ListAdapter<T, VH> where VH : RecyclerView.ViewHolder, VH : (T) -> Unit, T : Diffable<T> {
        val diffUtilItemCallback = object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.areItemsTheSame(newItem)

            override fun areContentsTheSame(oldItem: T, newItem: T) =
                oldItem.areContentsTheSame(newItem)
        }
        return object : ListAdapter<T, VH>(diffUtilItemCallback) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater
                .from(parent.context)
                .let { viewHolderCreator(it, parent) }

            override fun onBindViewHolder(holder: VH, position: Int) = holder(getItem(position))
        }
    }

    interface Diffable<T> {
        fun areItemsTheSame(item: T): Boolean
        fun areContentsTheSame(item: T): Boolean
    }
}