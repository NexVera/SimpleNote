package com.example.simplenote.domain.use_case

import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.repository.NoteRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note): Note? {
        var id: Int? = null

        if (note.context.isNotBlank()) {
            // Update the note title with the first line of context
            note.title = note.context.split("\n")[0]

            // Save the note
            id = repository.insertNote(note)

        } else {
            // Delete the note if context is blank
            repository.deleteNote(note)
        }

        // Return note if new note created in data source
        return if (id != null && note.id != id)
            Note(id, note.title, note.context)
        else
            null
    }
}