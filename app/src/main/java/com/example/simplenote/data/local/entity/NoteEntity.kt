package com.example.simplenote.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplenote.domain.model.Note

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var context: String
)

fun NoteEntity.toNote(): Note = Note(id, title, context)
