package com.example.simplenote.data.local.dao

import androidx.room.*
import com.example.simplenote.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity): Int

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity
}