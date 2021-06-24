package com.example.notes.framework.di

import com.example.notes.framework.viewmodels.ListViewModel
import com.example.notes.framework.viewmodels.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}