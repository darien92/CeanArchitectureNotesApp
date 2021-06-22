package com.example.notes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lib.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val content: String,
    @ColumnInfo(name = "creation_date")
    val creationTime: Long,
    @ColumnInfo(name = "Update_time")
    val updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
){
    companion object{
        fun fromNoteToEntity(note: Note) = NoteEntity(note.title, note.content, note.creationTime, note.updateTime)
    }
    fun toNoteFromEntity() = Note(title, content, creationTime, updateTime, id)
}
