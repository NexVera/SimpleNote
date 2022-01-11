package com.example.simplenote.domain.repository


import com.example.simplenote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note): Int

    suspend fun deleteNote(note: Note): Int

    fun getNotes(): Flow<List<Note>>

    suspend fun getNote(id: Int): Note
}