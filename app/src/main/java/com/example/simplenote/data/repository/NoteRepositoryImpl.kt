package com.example.simplenote.data.repository

import com.example.simplenote.data.local.dao.NoteDao
import com.example.simplenote.data.local.entity.toNote
import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.model.toNoteEntity
import com.example.simplenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.*

class NoteRepositoryImpl constructor(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note): Int = dao.insertNote(note.toNoteEntity()).toInt()

    override suspend fun deleteNote(note: Note): Int = dao.deleteNote(note.toNoteEntity())

    override fun getNotes(): Flow<List<Note>> = dao.getNotes().map { notes ->
        notes.map { note -> note.toNote() }
    }

    override suspend fun getNote(id: Int): Note = dao.getNoteById(id).toNote()
}