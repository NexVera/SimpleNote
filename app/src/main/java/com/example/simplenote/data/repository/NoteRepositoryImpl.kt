package com.example.simplenote.data.repository

import com.example.simplenote.data.data_source.local.Note
import com.example.simplenote.data.data_source.local.NoteDao
import com.example.simplenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl constructor(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note) = dao.insertNote(note)

    override suspend fun deleteNote(note: Note) = dao.deleteNote(note)

    override fun getNotes(): Flow<List<Note>> = dao.getNotes()

    override suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)
}