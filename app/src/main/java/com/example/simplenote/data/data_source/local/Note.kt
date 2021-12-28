package com.example.simplenote.data.data_source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var context: String,
)
