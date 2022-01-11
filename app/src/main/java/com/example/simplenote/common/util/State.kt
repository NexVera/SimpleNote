package com.example.simplenote.common.util

data class State<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)