package com.example.lib.usecases

import com.example.lib.data.Note
import com.example.lib.repository.NoteRepository

class RemoveNote (
    private val noteRepository: NoteRepository
        ) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}