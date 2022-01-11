package com.example.simplenote.common.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BindableListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallBack: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallBack) {
    // Will be implement by the list adapter for binding an event listener
    interface EventListener
    var eventListener: EventListener? = null
}