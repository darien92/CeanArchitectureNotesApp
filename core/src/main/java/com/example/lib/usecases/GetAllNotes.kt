package com.example.lib.usecases

import com.example.lib.repository.NoteRepository

class GetAllNotes(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke() = noteRepository.getAllNotes()
}