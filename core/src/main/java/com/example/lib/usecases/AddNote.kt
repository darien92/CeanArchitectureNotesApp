package com.example.lib.usecases

import com.example.lib.data.Note
import com.example.lib.repository.NoteRepository

class AddNote (
    private val noteRepository: NoteRepository
        ){
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}