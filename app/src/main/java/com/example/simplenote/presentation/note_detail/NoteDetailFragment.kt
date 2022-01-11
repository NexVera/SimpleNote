package com.example.simplenote.presentation.note_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simplenote.databinding.FragmentNoteDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private val viewModel: NoteDetailViewModel by viewModels()
    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { result ->
                // Bind latest state result
                binding.viewModel = viewModel

                // Handle if error occur
                if (result.error.isNotBlank())
                    Snackbar.make(view, result.error, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}