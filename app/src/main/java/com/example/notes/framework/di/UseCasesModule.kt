package com.example.notes.framework.di

import com.example.lib.repository.NoteRepository
import com.example.lib.usecases.AddNote
import com.example.lib.usecases.GetAllNotes
import com.example.lib.usecases.GetNote
import com.example.lib.usecases.RemoveNote
import com.example.notes.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )
}