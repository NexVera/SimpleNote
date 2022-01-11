package com.example.simplenote.presentation.note_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.simplenote.R
import com.example.simplenote.common.util.Constants
import com.example.simplenote.databinding.FragmentNoteListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment(), NoteListAdapter.EventListener {
    private val viewModel: NoteListViewModel by viewModels()
    private lateinit var binding: FragmentNoteListBinding

    @Inject
    lateinit var adapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { result ->
                // Bind latest state result
                binding.apply {
                    fragment = this@NoteListFragment
                    viewModel = this@NoteListFragment.viewModel
                }

                // Handle if error occur
                if (result.error.isNotBlank())
                    Snackbar.make(view, result.error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemClick(id: Int) {
        // Navigate to note detail
        bundleOf(Constants.PARAM_NOTE_ID to id).let { args ->
            findNavController().navigate(
                R.id.action_noteListFragment_to_noteDetailFragment,
                args
            )
        }
    }
}