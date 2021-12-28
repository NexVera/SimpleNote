package com.example.simplenote.domain.model

data class Note(
    val id: Int? = null,
    var title: String,
    var context: String,
)