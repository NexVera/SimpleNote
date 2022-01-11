package com.example.simplenote.common.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenote.common.util.BindableListAdapter

@BindingAdapter(value = ["adapter", "data"])
fun <T> bindListAdapterData(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?, data: List<T>?) {
    // Bind adapter
    recyclerView.adapter = adapter

    // Bind data
    @Suppress("UNCHECKED_CAST")
    (recyclerView.adapter as? ListAdapter<T, *>)?.submitList(data)
}

@BindingAdapter("eventListener")
fun bindEventListener(recyclerView: RecyclerView, eventListener: BindableListAdapter.EventListener) {
    (recyclerView.adapter as? BindableListAdapter<*, *>)?.eventListener = eventListener
}