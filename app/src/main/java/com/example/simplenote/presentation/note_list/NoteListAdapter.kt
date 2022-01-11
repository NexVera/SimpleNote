package com.example.simplenote.presentation.note_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenote.common.util.BindableListAdapter
import com.example.simplenote.databinding.ItemNoteListBinding
import com.example.simplenote.domain.model.Note
import javax.inject.Inject

class NoteListAdapter @Inject constructor() :
    BindableListAdapter<Note, NoteListAdapter.ViewHolder>(DiffCallBack) {

    class ViewHolder(
        private val binding: ItemNoteListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note, eventListener: EventListener?) {
            binding.apply {
                this.note = note
                this.eventListener = eventListener
            }
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    interface EventListener : BindableListAdapter.EventListener {
        fun onItemClick(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemNoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), eventListener as? EventListener)
    }
}