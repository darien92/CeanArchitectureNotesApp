package com.example.notes.framework.di

import android.app.Application
import com.example.lib.repository.NoteRepository
import com.example.notes.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}