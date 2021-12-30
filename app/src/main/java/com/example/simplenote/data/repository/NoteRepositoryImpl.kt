package com.example.simplenote.data.repository

import com.example.simplenote.data.local.dao.NoteDao
import com.example.simplenote.data.local.entity.toNote
import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.model.toNoteEntity
import com.example.simplenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl constructor(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note) = dao.insertNote(note.toNoteEntity())

    override suspend fun deleteNote(note: Note) = dao.deleteNote(note.toNoteEntity())

    override fun getNotes(): Flow<List<Note>> = dao.getNotes().map {
        it.map { note -> note.toNote() }
    }
}