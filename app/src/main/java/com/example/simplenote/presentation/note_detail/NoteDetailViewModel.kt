package com.example.simplenote.presentation.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenote.common.util.Constants
import com.example.simplenote.common.util.State
import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.use_case.GetNoteUseCase
import com.example.simplenote.domain.use_case.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableStateFlow<State<Note>> = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>(Constants.PARAM_NOTE_ID)?.let { id ->
            // Get existed note if not a new note (id == 0)
            if (id != 0)
                getNote(id)
            else
                _state.value = State(
                    data = Note(
                        id,
                        Constants.DEFAULT_VAL_STRING,
                        Constants.DEFAULT_VAL_STRING
                    )
                )
        }
    }

    private fun getNote(id: Int) {
        // Set the loading indicator
        _state.value = State(isLoading = true)

        // Get data from repository
        viewModelScope.launch {
            _state.value = State(data = getNoteUseCase(id))
        }
    }

    fun saveNote() {
        viewModelScope.launch {
            _state.value.data?.let { note ->
                saveNoteUseCase(note)?.let { newNote ->
                    // Update the state with new note created
                    _state.value = State(data = newNote)
                }
            }
        }
    }
}