package com.example.simplenote.domain.model

import com.example.simplenote.data.local.entity.NoteEntity

data class Note(
    val id: Int,
    var title: String,
    var context: String,
)

fun Note.toNoteEntity(): NoteEntity = NoteEntity(id, title, context)