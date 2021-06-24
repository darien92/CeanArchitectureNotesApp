package com.example.notes.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lib.data.Note
import com.example.notes.framework.UseCases
import com.example.notes.framework.di.ApplicationModule
import com.example.notes.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNote(){
        coroutineScope.launch {
            notes.postValue(useCases.getAllNotes())
        }
    }
}