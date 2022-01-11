package com.example.simplenote.common.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun bindVisibility(view: View, isVisible: Boolean) {
    when(isVisible) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}