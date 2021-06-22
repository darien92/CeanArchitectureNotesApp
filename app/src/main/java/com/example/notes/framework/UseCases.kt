package com.example.notes.framework

import com.example.lib.usecases.AddNote
import com.example.lib.usecases.GetAllNotes
import com.example.lib.usecases.GetNote
import com.example.lib.usecases.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)