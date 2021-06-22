package com.example.notes.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lib.data.Note
import com.example.lib.repository.NoteRepository
import com.example.lib.usecases.AddNote
import com.example.lib.usecases.GetAllNotes
import com.example.lib.usecases.GetNote
import com.example.lib.usecases.RemoveNote
import com.example.notes.framework.RoomNoteDataSource
import com.example.notes.framework.UseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    application: Application
): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))
    private val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )

    private val saved = MutableLiveData<Boolean>()

    fun saveNote(note: Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun isSaved(): LiveData<Boolean>{
        return saved
    }
}