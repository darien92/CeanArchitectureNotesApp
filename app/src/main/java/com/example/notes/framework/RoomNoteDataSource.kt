package com.example.notes.framework

import android.content.Context
import com.example.lib.data.Note
import com.example.lib.repository.NoteDataSource
import com.example.notes.framework.db.DatabaseService
import com.example.notes.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNoteToEntity(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNoteFromEntity()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntity().map { it.toNoteFromEntity() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNoteToEntity(note))
}