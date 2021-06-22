package com.example.lib.usecases

import com.example.lib.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id:Long) = noteRepository.getNote(id)
}