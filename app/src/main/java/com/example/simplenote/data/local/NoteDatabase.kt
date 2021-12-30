package com.example.simplenote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplenote.data.local.dao.NoteDao
import com.example.simplenote.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "note_db"
    }
}