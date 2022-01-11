package com.example.simplenote.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenote.common.util.State
import com.example.simplenote.domain.model.Note
import com.example.simplenote.domain.use_case.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<State<List<Note>>> = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        // Set the loading indicator
        _state.value = State(isLoading = true)

        // Get data from repository
        getNotesUseCase().onEach { notes ->
            _state.value = State(data = notes)
        }.launchIn(viewModelScope)
    }
}