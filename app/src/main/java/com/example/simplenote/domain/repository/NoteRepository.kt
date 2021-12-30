package com.example.simplenote.domain.repository


import com.example.simplenote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNotes(): Flow<List<Note>>
}