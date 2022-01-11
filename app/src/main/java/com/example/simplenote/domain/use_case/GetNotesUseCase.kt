package com.example.simplenote.domain.use_case

import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> = repository.getNotes()
}