package com.example.simplenote.domain.use_case

import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note = repository.getNote(id)
}